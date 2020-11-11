package es.david.marvel.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import es.david.marvel.R
import es.david.marvel.data.network.response.get_character_detail.Result
import es.david.marvel.data.util.StateData
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private val detailViewModel: DetailViewModel by viewModel()
    private val args: DetailFragmentArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel.getCharacterDetail(args.characterID)
    }

    private fun setupObservers() {
        detailViewModel.stateDetailLoad.observe(viewLifecycleOwner, { stateCharactersLoad ->
            when (stateCharactersLoad) {
                is StateData.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is StateData.Success -> {
                    setupView(stateCharactersLoad.data)
                    progressBar.visibility = View.GONE
                }
                is StateData.Error -> {
                    progressBar.visibility = View.GONE
                }
            }
        })
    }

    private fun setupView(character: Result) {
        characterName.text = character.name
        Picasso.get().load("${character.thumbnail.path}.${character.thumbnail.extension}")
            .fit()
            .centerCrop()
            .into(imageView2)
        characterDescription.text = character.description
    }
}