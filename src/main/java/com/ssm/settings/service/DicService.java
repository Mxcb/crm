package com.ssm.settings.service;

import com.ssm.settings.domain.DicValue;

import java.util.List;
import java.util.Map;

public interface DicService {
    Map<String, List<DicValue>> getDic();
}
