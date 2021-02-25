package com.factotum.rin.controller;

import com.factotum.rin.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
class FrequencyControllerIT {

    @Autowired
    private MockMvc mockMvc;

    private static final String BASE_URI = "/v1/frequency-types";

    @Test
    void getFrequencyTypes_GiveFrequencyTypesExist_ThenReturnAllTypes() throws Exception {
        mockMvc.perform(
                get(BASE_URI))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(5)))
                .andExpect(jsonPath("$[*].id", hasSize(5)))
                .andExpect(jsonPath("$[*].name", hasSize(5)));
    }

}
