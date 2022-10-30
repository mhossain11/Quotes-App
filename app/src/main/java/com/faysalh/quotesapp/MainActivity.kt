package com.faysalh.quotesapp

import android.app.ProgressDialog
import android.content.ClipData
import android.content.ClipboardManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.faysalh.quotesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var dialog :ProgressDialog?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        RequestManager(this@MainActivity).GetAllQuotes(listener)
        dialog= ProgressDialog(this@MainActivity)
        dialog!!.setTitle("Loading...")
        dialog!!.show()


    }
    private val listener :QuotesResponseListener =object :QuotesResponseListener{
        override fun didFetch(response: List<QuotesModel>, message: String) {
            dialog!!.dismiss()
            binding.Rcl.setHasFixedSize(true)
            binding.Rcl.layoutManager=StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
            binding.Rcl.adapter=QuoteAdapter(this@MainActivity,response,copyListener)


        }

        override fun didError(message: String) {
            dialog!!.dismiss()
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
        }

    }
    private val copyListener:CopyListener=object :CopyListener{
        override fun onCopyClicked(text: String) {

            val clipboard= getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("copied_data",text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this@MainActivity, "Copied", Toast.LENGTH_SHORT).show()

        }

    }
}