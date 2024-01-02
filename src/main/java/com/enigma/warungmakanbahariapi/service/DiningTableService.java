package com.enigma.warungmakanbahariapi.service;

import com.enigma.warungmakanbahariapi.entity.DiningTable;
import com.enigma.warungmakanbahariapi.model.request.DiningTableRequest;

import java.util.List;

public interface DiningTableService {
   DiningTable getOrSave(DiningTableRequest request);

   List<DiningTable> getAll();

   DiningTable getById(String id);

   DiningTable update(DiningTable request);

   String delete(String id);
}
