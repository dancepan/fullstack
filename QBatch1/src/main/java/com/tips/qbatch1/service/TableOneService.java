
package com.tips.qbatch1.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.tips.qbatch1.model.TableOne;
import com.tips.qbatch1.model.json.ReaderItemJson;

public interface TableOneService
{
	public void saveTableOneAllList(List<TableOne> tableOneList);
}