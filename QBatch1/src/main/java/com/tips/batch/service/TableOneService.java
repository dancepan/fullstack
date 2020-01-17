
package com.tips.batch.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.tips.batch.model.TableOne;
import com.tips.batch.model.json.ReaderItemJson;

public interface TableOneService
{
	public void saveTableOneAllList(List<TableOne> tableOneList);
}