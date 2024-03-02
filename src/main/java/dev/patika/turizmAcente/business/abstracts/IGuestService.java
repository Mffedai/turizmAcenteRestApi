package dev.patika.turizmAcente.business.abstracts;

import dev.patika.turizmAcente.dto.request.guest.GuestSaveRequest;
import dev.patika.turizmAcente.dto.request.guest.GuestUpdateRequest;
import dev.patika.turizmAcente.dto.response.guest.GuestResponse;
import dev.patika.turizmAcente.entity.Guest;

import java.util.Optional;

public interface IGuestService extends IBaseService<Guest, GuestSaveRequest, GuestUpdateRequest, GuestResponse>{
    Optional<Guest> findByMailAndTc(String mail, String tc);
}
