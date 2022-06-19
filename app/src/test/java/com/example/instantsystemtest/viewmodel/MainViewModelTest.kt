package com.example.instantsystemtest.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.instantsystemtest.ApiTest
import com.example.instantsystemtest.TestCoroutineRule
import com.example.instantsystemtest.api.json.Article
import com.example.instantsystemtest.model.Repository
import com.example.instantsystemtest.ui.main.viewmodel.MainViewModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()
    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var articlesResponseObserver: Observer<List<Article>>

    @Mock
    private lateinit var errorResponseObserver: Observer<String>


    @Before
    fun setUp() {
        viewModel = MainViewModel(repository)
    }


    @Test
    fun getSuccessArticlesList() {
        testCoroutineRule.runBlockingTest {
            viewModel.articlesList.observeForever(articlesResponseObserver)
            viewModel.errorStr.observeForever(errorResponseObserver)
            val apiTest = ApiTest()
            val response = apiTest.testOKGetTopHeadLines()
            viewModel.getListArticles();
            assertNotNull(viewModel.articlesList.getOrAwaitValue())
            assertEquals(response.body()?.articles, viewModel.articlesList.getOrAwaitValue())
        }
    }


    fun <T> LiveData<T>.getOrAwaitValue(
        time: Long = 2,
        timeUnit: TimeUnit = TimeUnit.SECONDS
    ): T {
        var data: T? = null
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(o: T?) {
                data = o
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }

        this.observeForever(observer)

        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

        @Suppress("UNCHECKED_CAST")
        return data as T
    }
}