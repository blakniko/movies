package com.gasto.movies.ui.view

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.gasto.movies.R
import com.gasto.movies.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment : Fragment() {
    private lateinit var viewBinding: FragmentSplashBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (viewBinding.animationpopcorn.drawable as AnimationDrawable).start()
        delaySplash(3000)
    }

    private fun delaySplash(seconds:Long){

        lifecycleScope.launch {
            delay(seconds)
            findNavController().navigate(R.id.action_splashFragment_to_moviesFragment)
        }
    }
}