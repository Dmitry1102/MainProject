package com.example.schoolorgonizer

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.schoolorgonizer.databinding.FragmentMainBinding
import java.lang.Exception

class MainFragment: Fragment() {

    private var binding: FragmentMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding?.root
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnCall?.setOnClickListener {
            makeCall("+3756744737")
        }

        //Будила
        binding!!.btnAlarmClock.setOnClickListener {
            it.findNavController().navigate(MainFragmentDirections.toAlarmClockFragment())

        }
        //Заметки
        binding!!.btnNotes.setOnClickListener {
            it.findNavController().navigate(MainFragmentDirections.toNoteFragment())
        }

        //Расписание
        binding!!.btnLessonSchedule.setOnClickListener {
            it.findNavController().navigate(MainFragmentDirections.actionMainFragmentToScheduleFragment())
        }

        binding!!.btnMap.setOnClickListener {
            it.findNavController().navigate(MainFragmentDirections.toMapFragment())
        }
    }
    companion object {
        const val TAG = "MainFragment"

    }

    private fun makeCall(number: String): Boolean{
        return try{
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$number")
            startActivity(intent)
            true
        }catch (e: Exception){
            val duration = Toast.LENGTH_SHORT
            Toast.makeText(context, "Phone number not found", duration).show()
            false
        }
    }

}
