package com.nvvi9.spoticloud.ui.youtube

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nvvi9.spoticloud.domain.GetYouTubeVideoItemsUseCase
import com.nvvi9.spoticloud.vo.YouTubeVideoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class YouTubeViewModel @Inject constructor(private val getYouTubeVideoItemsUseCase: GetYouTubeVideoItemsUseCase) :
    ViewModel() {

    val youTubeVideoItems: Flow<PagingData<YouTubeVideoItem>>
        get() = getYouTubeVideoItemsUseCase()
            .cachedIn(viewModelScope)
}