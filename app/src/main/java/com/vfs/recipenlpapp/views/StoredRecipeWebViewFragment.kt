package com.vfs.recipenlpapp.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.vfs.recipenlpapp.R
import com.vfs.recipenlpapp.models.Data
import com.vfs.recipenlpapp.models.Ingredient
import com.vfs.recipenlpapp.models.Recipe
import it.skrape.core.htmlDocument
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.skrape
import it.skrape.matchers.toBe
import it.skrape.selects.CssSelector
import it.skrape.selects.Doc
import it.skrape.selects.DocElement
import it.skrape.selects.attribute
import it.skrape.selects.html5.span
import org.apache.commons.lang3.mutable.Mutable


private const val ARG_RECIPE_INDEX = "recipeIndexParam"

open class StoredRecipeWebViewFragment : Fragment() {

    private var recipeIndex: Int? = null
    private lateinit var recipe : Recipe

    lateinit var javascriptInterface : MyJavaScriptInterface;

    private lateinit var webView : WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            recipeIndex = it.getInt(ARG_RECIPE_INDEX)
            recipe = Data.recipeList[recipeIndex!!]
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stored_recipe_web_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        javascriptInterface = MyJavaScriptInterface();
        webView = view.findViewById(R.id.storedRecipeWebView)!!
        webView.settings.javaScriptEnabled = true

        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                //webView.loadUrl(recipe.link)
                webView.loadUrl("javascript:window.HTMLViewer.processHTML('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
            }
        }
        webView.addJavascriptInterface(javascriptInterface, "HTMLViewer")
        webView.loadUrl(recipe.link);
    }

    fun getRecipeFromPage() : Recipe {
        val ingredients1 : MutableList<Ingredient> = mutableListOf(Ingredient("Sugar"), Ingredient("flour"), Ingredient("Baking Soda"))
        var recipe = Recipe(
            "test1",
            "https://www.allrecipes.com/recipe/8459990/rutabaga-beef-stew/",
            ingredients1
        )
        return recipe
    }

    companion object {
        @JvmStatic
        fun newInstance(recipeIndex: Int) =
            StoredRecipeWebViewFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_RECIPE_INDEX, recipeIndex)
                }
            }
    }
}

class MyJavaScriptInterface {

    var html: String? = null
    //lateinit var ingredientsDoc: List<DocElement>;

    @JavascriptInterface
    fun processHTML(html: String?) {
        this.html = html

        if (html != null) {
            var test = htmlDocument(html) {
                span {
                    findAll {
                        withAttribute = "data-ingredient-name" to "true"
                    }
                }
            }

              //Log.e("MyTag", test)
//            for (DocEle in ingredientsDoc) {
//                Log.e("MyTag", DocEle.html)
//            }
        }
    }
}