package com.faysalh.quotesapp

interface QuotesResponseListener {
    fun didFetch(response:List<QuotesModel>,message:String)
    fun didError(message:String)
}