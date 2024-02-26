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
    @NotNull
    private Integer hotelId;
    @NotNull
    private Integer pensionId;
    @NotNull
    private Integer sessionId;
    @NotNull
    private Room.Type type;
}
