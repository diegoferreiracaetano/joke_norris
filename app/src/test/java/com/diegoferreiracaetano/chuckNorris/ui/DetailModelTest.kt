package com.diegoferreiracaetano.chuckNorris.ui


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.diegoferreiracaetano.chuckNorris.mock.ChuckNorrisMother
import com.diegoferreiracaetano.chuckNorris.ui.detail.DetailViewModel
import com.diegoferreiracaetano.domain.joke.interactor.GetJokeInteractor
import io.reactivex.Maybe
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


class DetailModelTest {

    @Mock
    private lateinit var getJokeInteractor: GetJokeInteractor

    private lateinit var viewModel: DetailViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        viewModel = DetailViewModel(getJokeInteractor)
    }

    @Test
    @Throws(Exception::class)
    fun `Given joke, When load joke, Should update joke`() {

        // Given

        val category =  ChuckNorrisMother.fakeCategories[0]
        val joke = ChuckNorrisMother.fakeJoke

        `when`(getJokeInteractor.execute(any(GetJokeInteractor.Request::class.java))).thenReturn(Maybe.just(joke))

        // When

        viewModel.getJoke(category)

        // Should

        assertThat(joke, `is`(viewModel.joke.value))
    }

    @Test
    @Throws(Exception::class)
    fun `Given error emission, When load joke with error, Should update error`() {

        //Given

        val category =  ChuckNorrisMother.fakeCategories[0]
        val e = Exception()

        `when`(getJokeInteractor.execute(any(GetJokeInteractor.Request::class.java))).thenReturn(Maybe.error(e))

        // When

        viewModel.getJoke(category)

        // Should

        assertThat(e, `is`(viewModel.error.value))
    }

    @Test
    @Throws(Exception::class)
    fun  `Given unknown emission, When load categories with unknown error, Should update empty`() {

        // Given
        val category =  ChuckNorrisMother.fakeCategories[0]

        `when`(getJokeInteractor.execute(any(GetJokeInteractor.Request::class.java))).thenReturn(Maybe.empty())

        // When

        viewModel.getJoke(category)

        // Should

        assertThat(null, `is`(viewModel.joke.value))
    }

}
