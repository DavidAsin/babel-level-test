package es.david.marvel.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.david.marvel.R
import es.david.marvel.data.util.StateData
import es.david.marvel.ui.characters.adapter.CharacterAdapter
import kotlinx.android.synthetic.main.fragment_characters.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFragment : Fragment() {

    private val charactersViewModel: CharactersViewModel by viewModel()

    private val characterAdapter = CharacterAdapter {
        val action = CharactersFragmentDirections.actionNavigationHomeToNavigationDashboard(
            characterID = it.id
        )
        findNavController().navigate(action)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupObservers() {
        charactersViewModel.stateCharactersLoad.observe(viewLifecycleOwner, { stateCharactersLoad ->
            when (stateCharactersLoad) {
                is StateData.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is StateData.Success -> {
                    characterAdapter.updateData(stateCharactersLoad.data)
                    progressBar.visibility = View.GONE
                }
                is StateData.Error -> {
                    progressBar.visibility = View.GONE
                }
            }
        })
    }

    private fun setupRecyclerView() {
        rvCharacters.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = characterAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val totalItemCount = (layoutManager as GridLayoutManager).itemCount
                    val lastVisibleItem =
                        (layoutManager as GridLayoutManager).findLastVisibleItemPosition()
                    if (charactersViewModel.stateCharactersLoad.value !is StateData.Loading && totalItemCount <= (lastVisibleItem + 5)) {
                        charactersViewModel.getCharacters()
                    }
                }

            })
        }
    }

}