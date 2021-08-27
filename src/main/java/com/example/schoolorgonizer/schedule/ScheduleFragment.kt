package com.example.schoolorgonizer.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schoolorgonizer.databinding.FragmentScheduleBinding
import com.example.schoolorgonizer.schedule.databaseLesson.Data
import com.example.schoolorgonizer.schedule.scheduleViewModel.ScheduleViewModel
import com.example.schoolorgonizer.schedule.recycle.ScheduleAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class ScheduleFragment : Fragment() {

    private var binding: FragmentScheduleBinding? = null
    private val viewModel: ScheduleViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScheduleBinding.inflate(inflater, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.btnEdit?.setOnClickListener {
            it.findNavController()
                .navigate(ScheduleFragmentDirections.actionScheduleFragmentToFragmentAdd())
        }


        binding?.rvSchedule?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val scheduleAdapter = ScheduleAdapter { data ->
            binding?.btnDelete?.setOnClickListener {
                click(data)
            }
        }
        binding?.rvSchedule?.adapter = scheduleAdapter
        viewModel.liveData.observe(this.viewLifecycleOwner) {
            scheduleAdapter.submitList(it)
        }


        binding?.btnReturn?.setOnClickListener {
            it.findNavController().popBackStack()
        }
    }

    private fun click(data: Data) {
        viewModel.deleteFromDataBase(data)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


}