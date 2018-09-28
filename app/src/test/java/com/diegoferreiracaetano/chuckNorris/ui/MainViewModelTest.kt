package com.diegoferreiracaetano.chuckNorris.ui


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.diegoferreiracaetano.chuckNorris.mock.ChuckNorrisMother
import com.diegoferreiracaetano.chuckNorris.ui.main.MainViewModel
import com.diegoferreiracaetano.domain.joke.interactor.GetListCategoriesInteractor
import io.reactivex.Flowable
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


class MainViewModelTest {

    @Mock
    private lateinit var getListCategoriesInteractor: GetListCategoriesInteractor

    private lateinit var viewModel: MainViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        viewModel = MainViewModel(getListCategoriesInteractor)
    }

    @Test
    @Throws(Exception::class)
    fun `Given categories, When load categories, Should update categories`() {

        // Given

        val categories =  ChuckNorrisMother.fakeCategories

        `when`(getListCategoriesInteractor.execute(any(GetListCategoriesInteractor.Request::class.java))).thenReturn(Flowable.just(categories))


        // When

        viewModel.getCategories()

        // Should

        assertThat(categories, `is`(viewModel.categories.value))
    }


    @Test
    @Throws(Exception::class)
    fun `Given error emission, When load categories with error, Should update error`() {

        //Given

        val e = Exception()

        `when`(getListCategoriesInteractor.execute(any(GetListCategoriesInteractor.Request::class.java))).thenReturn(Flowable.error(e))

        // When

        viewModel.getCategories()

        // Should

        assertThat(e, `is`(viewModel.error.value))
    }

    @Test
    @Throws(Exception::class)
    fun  `Given unknown emission, When load categories with unknown error, Should update empty`() {

        // Given


        `when`(getListCategoriesInteractor.execute(any(GetListCategoriesInteractor.Request::class.java))).thenReturn(Flowable.empty())

        // When

        viewModel.getCategories()

        // Should

        assertThat(null, `is`(viewModel.empty.value))
    }
}
