package com.enigma.warungmakanbahariapi.service;

import com.enigma.warungmakanbahariapi.constan.ERole;
import com.enigma.warungmakanbahariapi.entity.Role;

public interface RoleService {

    Role getOrSave(ERole role);

}
