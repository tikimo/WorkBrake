package fi.utu.tikimo.health.workbrake.data.local.model;

import java.time.LocalTime;

public enum AlarmHours {
    BRAKE1(LocalTime.of(7,45)),
    BRAKE2(LocalTime.of(8,45)),

    ;



    private LocalTime localTime;

    AlarmHours(LocalTime localTime) {
        this.localTime = localTime;
    }


}
