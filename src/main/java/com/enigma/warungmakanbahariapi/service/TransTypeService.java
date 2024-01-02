package com.enigma.warungmakanbahariapi.service;

import com.enigma.warungmakanbahariapi.entity.TransType;

public interface TransTypeService {
    TransType getOrSave(String type);
}
