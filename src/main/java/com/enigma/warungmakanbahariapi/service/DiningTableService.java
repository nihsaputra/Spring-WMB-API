package com.enigma.warungmakanbahariapi.service;

import com.enigma.warungmakanbahariapi.entity.DiningTable;
import com.enigma.warungmakanbahariapi.model.request.DiningTableRequest;

public interface DiningTableService {
   DiningTable create(DiningTableRequest name);
}
