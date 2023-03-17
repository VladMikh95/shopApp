package ml.vladmikh.projects.shopapp.ui.views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ml.vladmikh.projects.shopapp.component
import ml.vladmikh.projects.shopapp.databinding.FragmentMainPageBinding
import ml.vladmikh.projects.shopapp.ui.adapters.ShopAppRecyclerViewAdapter
import ml.vladmikh.projects.shopapp.ui.viewmodel.ShopAppViewModel
import ml.vladmikh.projects.shopapp.ui.viewmodel.ShopAppViewModelFactory
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainPageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentMainPageBinding

    @Inject
    lateinit var factory : ShopAppViewModelFactory
    private val viewModel: ShopAppViewModel by lazy {
       ViewModelProvider(this, factory).get(ShopAppViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainPageBinding.inflate(inflater, container, false)

        val recyclerviewLatestProducts = binding.recyclerViewLatestProduct
        val recyclerviewProductsOnDiscont = binding.recyclerViewProductsOnDiscont

        val adapterLatestProduct = ShopAppRecyclerViewAdapter()
        val adapterProductsOnDiscont = ShopAppRecyclerViewAdapter()

        recyclerviewLatestProducts.adapter = adapterLatestProduct
        recyclerviewProductsOnDiscont.adapter = adapterProductsOnDiscont

        viewModel.getLatestProduct().observe(viewLifecycleOwner) {
            adapterLatestProduct.refreshProducts(it)
        }

        viewModel.getProductOnDiscont().observe(viewLifecycleOwner) {
            adapterProductsOnDiscont.refreshProducts(it)
        }

        viewModel.getDataAboutProductsFromNetwork()

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainPageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainPageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}