package dev.patika.turizmAcente.dto.request.room;

import dev.patika.turizmAcente.entity.Room;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomSaveRequest {
    @NotNull
    private int stock;
    @NotNull
    private int bed;
    @NotNull
    private int mtrsqr;
    @NotNull
    private int prc_chld;
    @NotNull
    private int prc_adult;
    @NotNull
    private boolean aircndtn;
    @NotNull
    private boolean minibar;
    @NotNull
    private boolean tv;
    @NotNull
    private boolean safe;
    @NotNull
    private boolean fridge;
    private Integer hotelId;
    private Integer pensionId;
    private Integer sessionId;
    @NotNull
    private Room.Type type;
}
