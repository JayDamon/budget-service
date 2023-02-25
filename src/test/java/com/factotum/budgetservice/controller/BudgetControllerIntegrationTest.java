package com.factotum.budgetservice.controller;

import com.factotum.budgetservice.IntegrationTest;
import com.factotum.budgetservice.dto.BudgetCategoryDto;
import com.factotum.budgetservice.dto.BudgetDto;
import com.factotum.budgetservice.dto.TransactionTotal;
import com.factotum.budgetservice.enumeration.BudgetType;
import com.factotum.budgetservice.http.TransactionService;
import com.factotum.budgetservice.model.BudgetCategory;
import com.factotum.budgetservice.repository.BudgetCategoryRepository;
import com.factotum.budgetservice.util.SecurityTestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
class BudgetControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BudgetCategoryRepository budgetCategoryRepository;

    @MockBean
    private TransactionService transactionService;

    private static final String URI = "/v1/budgets";

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Set<BudgetDto> basicBudgetDtos = new HashSet<>();
    private BudgetDto basicBudgetDto;
    private BudgetDto completeBudgetDto;

    private static final String budgetIdOne = "19735b1a-16a5-44a8-ac2f-8ef6e0efd05f";
    private static final String budgetIdTwenty = "33c31199-202a-4443-8f09-417ea8acd7d3";
    private static final UUID budgetIdUuidTwenty = UUID.fromString(budgetIdTwenty);
    private static final UUID budgetCategoryIdOne = UUID.fromString("5c07c147-1aab-472b-9c8f-e500d3161210");
    private static final UUID budgetCategoryIdSix = UUID.fromString("2bcb4c13-12db-41b0-92ad-f9a332db3bf9");
    private static final UUID frequencyTypeIdOne = UUID.fromString("daf7d54a-1cea-4e1d-818a-bfde558870d7");
    private static final UUID frequencyTypeIdTwo = UUID.fromString("f5b475fe-3de6-4df1-8eaa-dba249450b58");

    @BeforeEach
    void setUp() {
        objectMapper.findAndRegisterModules();

        BudgetCategoryDto categoryDto = new BudgetCategoryDto(budgetCategoryIdSix, "flexible", "spending");
        basicBudgetDto = new BudgetDto();
        basicBudgetDto.setName("TestName");
        basicBudgetDto.setBudgetCategory(categoryDto);
        basicBudgetDto.setEndDate(ZonedDateTime.now());
        basicBudgetDto.setStartDate(ZonedDateTime.now());
        basicBudgetDtos.add(basicBudgetDto);

        BudgetCategoryDto budgetCategoryDto = new BudgetCategoryDto();
        budgetCategoryDto.setId(budgetCategoryIdOne);

        completeBudgetDto = new BudgetDto(budgetIdUuidTwenty, "newName", budgetCategoryDto, ZonedDateTime.now(), ZonedDateTime.now().plusDays(1),
                frequencyTypeIdOne, null, BigDecimal.valueOf(100), true);
    }

    // getActiveBudgets
    @Test
    void getActiveBudgets_GivenBudgetsExist_ThenReturnOkWithBudgetDto() throws Exception {

        mockMvc.perform(
                get(URI)
                        .with(jwt().jwt(SecurityTestUtil.getTestJwt())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].name").exists())
                .andExpect(jsonPath("$[0].budgetCategory.id").exists())
                .andExpect(jsonPath("$[0].budgetCategory.type").exists())
                .andExpect(jsonPath("$[0].budgetCategory.name").exists())
                .andExpect(jsonPath("$[0].budgetCategory.budgetItems").exists())
                .andExpect(jsonPath("$[0].startDate").exists())
                .andExpect(jsonPath("$[0].frequencyType").exists())
                .andExpect(jsonPath("$[0].amount").exists())
                .andExpect(jsonPath("$[0].inUse").exists());

    }

    // getBudgetById
    @Test
    void getBudgetById_GivenBudgetExists_ThenReturnBudget() throws Exception {

        mockMvc.perform(
                get(URI + "/{id}", budgetIdOne)
                        .with(jwt().jwt(SecurityTestUtil.getTestJwt())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.budgetCategory").exists())
                .andExpect(jsonPath("$.budgetCategory.id").exists())
                .andExpect(jsonPath("$.budgetCategory.type").exists())
                .andExpect(jsonPath("$.budgetCategory.name").exists())
                .andExpect(jsonPath("$.budgetCategory.budgetItems").exists());

    }

    @Test
    void getBudgetById_GivenBudgetDoesNotExist_ThenReturnNotFound() throws Exception {
        mockMvc.perform(
                get(URI + "/{id}", UUID.randomUUID())
                        .with(jwt().jwt(SecurityTestUtil.getTestJwt())))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    // createNewBudgets
    @Test
    void createNewBudgets_GivenSimpleBudgetProvided_ThenCreateBudgetsAndReturnWithIds() throws Exception {

        MvcResult mv = mockMvc.perform(
                post(URI)
                        .content(objectMapper.writeValueAsString(basicBudgetDtos))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(jwt().jwt(SecurityTestUtil.getTestJwt())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].name").exists())
                .andExpect(jsonPath("$[0].budgetCategory.id").exists())
                .andReturn();

        ArrayNode node = (ArrayNode) objectMapper.readTree(mv.getResponse().getContentAsString());
        UUID id = UUID.fromString(node.get(0).get("budgetCategory").get("id").asText());

        BudgetCategory b = budgetCategoryRepository.findById(id).orElse(null);
        assertThat(b, is(not(nullValue())));
        assertThat(b.getName().getName(), is(equalTo("spending")));
        assertThat(b.getType().getName(), is(equalTo("flexible")));

    }

    @Test
    void createNewBudgets_GivenBudgetMissingCategory_ThenReturnBadRequest() throws Exception {

        this.basicBudgetDto.setBudgetCategory(null);

        mockMvc.perform(
                        post(URI)
                                .content(objectMapper.writeValueAsString(basicBudgetDtos))
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(jwt().jwt(SecurityTestUtil.getTestJwt())))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    void createNewBudgets_GivenBudgetCategoryIsMissingId_ThenReturnBadRequest() throws Exception {

        this.basicBudgetDto.setBudgetCategory(new BudgetCategoryDto());

        mockMvc.perform(
                        post(URI)
                                .content(objectMapper.writeValueAsString(basicBudgetDtos))
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(jwt().jwt(SecurityTestUtil.getTestJwt())))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    void createNewBudgets_GivenNoBudgetProvided_ThenReturnBadRequest() throws Exception {
        mockMvc.perform(
                post(URI)
                        .with(jwt().jwt(SecurityTestUtil.getTestJwt())))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    // updateBudget
    @Test
    void updateBudget_GivenValidBudgetProvided_ThenReturnUpdatedBudget() throws Exception {

        mockMvc.perform(
                patch(URI + "/{id}", budgetIdTwenty)
                        .content(objectMapper.writeValueAsString(completeBudgetDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(jwt().jwt(SecurityTestUtil.getTestJwt())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(equalTo(budgetIdTwenty))))
                .andExpect(jsonPath("$.name", is(equalTo("newName"))))
                .andExpect(jsonPath("$.budgetCategory.id", is(equalTo(budgetCategoryIdOne.toString()))))
                .andExpect(jsonPath("$.budgetCategory.type", is(equalTo("fixed"))))
                .andExpect(jsonPath("$.budgetCategory.name", is(equalTo("income"))))
                .andExpect(jsonPath("$.budgetCategory.budgetItems").exists())
                .andExpect(jsonPath("$.startDate").exists())
                .andExpect(jsonPath("$.endDate").exists())
                .andExpect(jsonPath("$.frequencyTypeId", is(equalTo(frequencyTypeIdOne.toString()))))
                .andExpect(jsonPath("$.frequencyType", is(equalTo("Weekly"))))
                .andExpect(jsonPath("$.amount", is(equalTo(100))))
                .andExpect(jsonPath("$.inUse", is(true)));
    }

    @Test
    void updateBudget_GivenOnlySomeFieldsChanged_ThenReturnUpdatedBudgetWithNullFieldsUnchanced() throws Exception {

        basicBudgetDto.setId(budgetIdUuidTwenty);

        mockMvc.perform(
                patch(URI + "/{id}", budgetIdTwenty)
                        .content(objectMapper.writeValueAsString(basicBudgetDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(jwt().jwt(SecurityTestUtil.getTestJwt())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(equalTo(budgetIdTwenty))))
                .andExpect(jsonPath("$.name", is(equalTo("TestName"))))
                .andExpect(jsonPath("$.budgetCategory.id", is(equalTo(budgetCategoryIdSix.toString()))))
                .andExpect(jsonPath("$.budgetCategory.type", is(equalTo("flexible"))))
                .andExpect(jsonPath("$.budgetCategory.name", is(equalTo("spending"))))
                .andExpect(jsonPath("$.budgetCategory.budgetItems").exists())
                .andExpect(jsonPath("$.startDate").exists())
                .andExpect(jsonPath("$.endDate").exists())
                .andExpect(jsonPath("$.frequencyTypeId", is(equalTo(frequencyTypeIdTwo.toString()))))
                .andExpect(jsonPath("$.frequencyType", is(equalTo("Monthly"))))
                .andExpect(jsonPath("$.amount", is(equalTo(3000.0))))
                .andExpect(jsonPath("$.inUse", is(true)));
    }

    @Test
    void updateBudget_GivenBudgetJsonIdAndPathIdDontMatch_TheReturnBadRequest() throws Exception {

        mockMvc.perform(
                patch(URI + "/{id}", "1")
                        .content(objectMapper.writeValueAsString(completeBudgetDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(jwt().jwt(SecurityTestUtil.getTestJwt())))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateBudget_GivenBudgetIdNotInBody_TheReturnBadRequest() throws Exception {

        completeBudgetDto.setId(null);

        mockMvc.perform(
                patch(URI + "/{id}", "1")
                        .content(objectMapper.writeValueAsString(completeBudgetDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(jwt().jwt(SecurityTestUtil.getTestJwt())))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateBudget_GivenPathIdLessThanOne_TheReturnBadRequest() throws Exception {

        mockMvc.perform(
                patch(URI + "/{id}", "0")
                        .content(objectMapper.writeValueAsString(completeBudgetDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(jwt().jwt(SecurityTestUtil.getTestJwt())))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    // getBudgetSummary
    @Test
    void getBudgetSummary_GivenBudgetsExist_ThenReturnBudgetSummary() throws Exception {

        int expectedSize = 5;

        when(transactionService.getTransactionTotal(anyInt(), anyInt(), any(), any())).thenAnswer(i -> new TransactionTotal(BudgetType.EXPENSE, BigDecimal.ONE));

        mockMvc.perform(
                get(URI + "/summary")
                        .param("year", "2017")
                        .param("month", "1")
                        .with(jwt().jwt(SecurityTestUtil.getTestJwt())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*]", hasSize(expectedSize)))
                .andExpect(jsonPath("$.[*].category", hasSize(expectedSize)))
                .andExpect(jsonPath("$.[*].month", hasSize(expectedSize)))
                .andExpect(jsonPath("$.[*].monthText", hasSize(expectedSize)))
                .andExpect(jsonPath("$.[*].year", hasSize(expectedSize)))
                .andExpect(jsonPath("$.[*].planned", hasSize(expectedSize)))
                .andExpect(jsonPath("$.[*].actual", hasSize(expectedSize)))
                .andExpect(jsonPath("$.[*].expected", hasSize(expectedSize)));

    }

    @Test
    void getBudgetSummary_GivenNoBudgetsExist_ThenReturnBudgetSummary() throws Exception {

        mockMvc.perform(
                get(URI + "/summary")
                        .param("year", "2016")
                        .param("month", "1")
                        .with(jwt().jwt(SecurityTestUtil.getTestJwt())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*]", hasSize(0)));

    }

    @Test
    void getBudgetSummary_GivenYearProvidedWithNoMonth_ThenReturnBadRequest() throws Exception {

        mockMvc.perform(
                get(URI + "/summary")
                        .param("year", "2018")
                        .with(jwt().jwt(SecurityTestUtil.getTestJwt())))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    void getBudgetSummary_GivenMonthProvidedWithNoYear_ThenReturnBadRequest() throws Exception {

        mockMvc.perform(
                get(URI + "/summary")
                        .param("month", "1")
                        .with(jwt().jwt(SecurityTestUtil.getTestJwt())))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

}
