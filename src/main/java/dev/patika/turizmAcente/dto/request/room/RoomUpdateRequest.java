package dev.patika.turizmAcente.dto.request.room;

import dev.patika.turizmAcente.entity.Room;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomUpdateRequest {
    @Positive(message = "ID değeri pozitif olmalı")
    private Long id;
    private int stock;
    private int bed;
    private int mtrsqr;
    private int prc_chld;
    private int prc_adult;
    private boolean aircndtn;
    private boolean minibar;
    private boolean tv;
    private boolean safe;
    private boolean fridge;
    private Integer hotelId;
    private Integer pensionId;
    private Integer sessionId;
    private Room.Type type;
}
