package com.systemicr2.footballclubmanagementapi.service;

import com.systemicr2.footballclubmanagementapi.model.Player;
import com.systemicr2.footballclubmanagementapi.model.TrainingAttendance;
import com.systemicr2.footballclubmanagementapi.model.TrainingSession;
import com.systemicr2.footballclubmanagementapi.model.enums.AttendanceStatus;
import com.systemicr2.footballclubmanagementapi.repository.TrainingAttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingAttendanceService {

    private final TrainingAttendanceRepository attendanceRepository;

    public TrainingAttendance markAttendance(TrainingSession session, Player player, AttendanceStatus status, String notes) {

        TrainingAttendance record = new TrainingAttendance();
        record.setTrainingSession(session);
        record.setPlayer(player);
        record.setStatus(status);
        record.setNotes(notes);

        return attendanceRepository.save(record);
    }
    public List<TrainingAttendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }
}

