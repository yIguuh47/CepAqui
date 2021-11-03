package com.example.cepaqui.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.example.cepaqui.R
import com.example.cepaqui.databinding.FragmentHomeBinding
import com.example.cepaqui.model.CepModel
import com.example.cepaqui.remote.ConsultCep
import com.example.cepaqui.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.ibtnHistoric.setOnClickListener {
            findNavController().navigate(R.id.navigationToHistoricalFragment)
        }

        binding.btnSet.setOnClickListener {
            val cep =  binding.etCep.text.toString()
            getCep(cep)
        }
        return binding.root
    }

    val cepsList = MutableLiveData<CepModel>()
    val errorMessage = MutableLiveData<String>()

    fun getCep(cep: String) {
        val service = RetrofitClient.createService(ConsultCep::class.java)
        val call = service.getDiceCity(cep)

        call.enqueue(object : Callback<CepModel> {
            override fun onResponse(call: Call<CepModel>,
                                    response: Response<CepModel>){
                if (response?.code()==200){
                    val responseCep = response.body()!!
                    cepsList.postValue(responseCep)
                    binding.tvNomeEndereco.text = "Nome de Endereço: ${responseCep.addressName}"
                    binding.tvTipoEndereco.text = "Tipo de Endereço: ${responseCep.addressType}"
                    binding.tvEndereco.text = "Endereço: ${responseCep.address}"
                    binding.tvCidade.text = "Cidade: ${responseCep.city}"
                    binding.tvDistrito.text = "Distrito: ${responseCep.district}"
                    binding.tvEstado.text = "Estado: ${responseCep.state}"
                }else{
                    errorMessage.postValue("Erro ${response.code()}")
                }
            }

            override fun onFailure(call: Call<CepModel>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

}