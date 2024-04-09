package com.example.memerest.data

import javax.inject.Inject

class MemeRepository @Inject constructor(){
    fun memePagingSource() = MemePagingSource()
}