package com.factotum.rin.util;

import com.factotum.rin.dto.BudgetCategoryDto;
import com.factotum.rin.dto.BudgetDto;
import com.factotum.rin.dto.BudgetItemDto;
import com.factotum.rin.model.Budget;
import com.factotum.rin.model.BudgetCategory;
import com.factotum.rin.model.BudgetCategoryName;
import com.factotum.rin.model.BudgetCategoryType;
import com.factotum.rin.model.BudgetItem;
import com.factotum.rin.model.FrequencyType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.oneOf;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BudgetUtilUT {

    //    convertBudgetsToDto
    @Test
    void convertBudgetsToDto_GivenValidBudgets_ThenReturnFullDtos() {

        // Arrange
        Budget budgetOne = createBudget(
                "A Budget Name", ZonedDateTime.now(), ZonedDateTime.now().plusDays(5),
                35.02, true, 1L);

        Budget budgetTwo = createBudget(
                "A Second Budget Name", ZonedDateTime.now(), ZonedDateTime.now().plusDays(8),
                22.22, true, 2L);

        // Act
        Set<BudgetDto> budgetDtos = BudgetUtil.convertBudgetsToDto(Arrays.asList(budgetOne, budgetTwo));

        // Assert
        assertThat(budgetDtos, hasSize(2));

        int dtosChecked = 0;
        for (BudgetDto dto : budgetDtos) {
            assertThat(dto.getId(), is(oneOf(1L, 2L)));
            assertThat(dto.getName(), is(oneOf("A Budget Name", "A Second Budget Name")));
            assertThat(dto.getStartDate().getDayOfMonth(), is(LocalDate.now().getDayOfMonth()));
            assertThat(dto.getEndDate().getDayOfMonth(), is(oneOf(LocalDate.now().plusDays(8).getDayOfMonth(), ZonedDateTime.now().plusDays(5).getDayOfMonth())));
            assertThat(dto.getFrequencyTypeId(), is(equalTo(3)));
            assertThat(dto.getFrequencyTypeName(), is(equalTo("TestFrequencyTypeName")));
            assertThat(dto.getAmount(), is(oneOf(BigDecimal.valueOf(35.02), BigDecimal.valueOf(22.22))));
            assertThat(dto.getInUse(), is(true));

            BudgetCategoryDto cat = dto.getBudgetCategory();
            assertThat(cat.getId(), is(equalTo(1)));
            assertThat(cat.getTypeName(), is(equalTo("TestBudgetCategoryType")));
            assertThat(cat.getName(), is(equalTo("TestBudgetCategoryName")));

            Set<BudgetItemDto> items = cat.getBudgetItems();
            assertThat(items, hasSize(2));
            int itemsChecked = 0;
            for (BudgetItemDto itemDto : items) {
                assertThat(itemDto.getId(), is(oneOf(6, 7)));
                assertThat(itemDto.getCategoryName(), is(equalTo("TestBudgetCategoryName")));
                assertThat(itemDto.getName(), is(oneOf("Item Name Two", "Item Name")));
                itemsChecked++;
            }
            assertThat(itemsChecked, is(equalTo(2)));

            dtosChecked++;
        }
        assertThat(dtosChecked, is(equalTo(2)));

    }

    //    convertBudgetToDto
    @Test
    void convertBudgetToDto_GivenValidBudgetProvided_ThenReturnBudgetDto() {

        // Arrange
        Budget budgetOne = createBudget(
                "A Budget Name", ZonedDateTime.now(), ZonedDateTime.now().plusDays(5),
                35.02, true, 1L);

        // Act
        BudgetDto dto = BudgetUtil.convertBudgetToDto(budgetOne);

        // Assert
        assertThat(dto.getId(), is(equalTo(1L)));
        assertThat(dto.getName(), is(equalTo("A Budget Name")));
        assertThat(dto.getStartDate().getDayOfMonth(), is(equalTo(LocalDate.now().getDayOfMonth())));
        assertThat(dto.getEndDate().getDayOfMonth(), is(equalTo(ZonedDateTime.now().plusDays(5).getDayOfMonth())));
        assertThat(dto.getFrequencyTypeId(), is(equalTo(3)));
        assertThat(dto.getFrequencyTypeName(), is(equalTo("TestFrequencyTypeName")));
        assertThat(dto.getAmount(), is(equalTo(BigDecimal.valueOf(35.02))));
        assertThat(dto.getInUse(), is(true));

        BudgetCategoryDto cat = dto.getBudgetCategory();
        assertThat(cat.getId(), is(equalTo(1)));
        assertThat(cat.getTypeName(), is(equalTo("TestBudgetCategoryType")));
        assertThat(cat.getName(), is(equalTo("TestBudgetCategoryName")));

        Set<BudgetItemDto> items = cat.getBudgetItems();
        assertThat(items, hasSize(2));
        int itemsChecked = 0;
        for (BudgetItemDto itemDto : items) {
            assertThat(itemDto.getId(), is(oneOf(6, 7)));
            assertThat(itemDto.getCategoryName(), is(equalTo("TestBudgetCategoryName")));
            assertThat(itemDto.getName(), is(oneOf("Item Name Two", "Item Name")));
            itemsChecked++;
        }
        assertThat(itemsChecked, is(equalTo(2)));
    }

    @Test
    void convertBudgetToDto_GivenBudgetIsNull_ThenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> BudgetUtil.convertBudgetToDto(null));
    }

    //    convertBudgetDtosToBudget
    @Test
    void convertBudgetDtosToBudget_GivenFullDtosProvided_ThenConvertToBudgetAndReturn() {

        // Arrange
        BudgetCategoryDto budgetCategoryDto = new BudgetCategoryDto(5, "TestType", "TestCatName");
        BudgetDto budgetDto = new BudgetDto(1L, "TestName", budgetCategoryDto, ZonedDateTime.now(),
                ZonedDateTime.now().plusDays(6), 10, "TestFrequency", BigDecimal.valueOf(54.21), false);

        // Act
        Set<Budget> budgets = BudgetUtil.convertBudgetDtosToBudget(Collections.singletonList(budgetDto));

        // Assert
        assertThat(budgets, hasSize(1));
        int budgetsChecked = 0;
        for (Budget b : budgets) {
            assertThat(b.getId(), is(equalTo(1L)));
            assertThat(b.getName(), is(equalTo("TestName")));
            assertThat(b.getStartDate().getDayOfMonth(), is(equalTo(ZonedDateTime.now().getDayOfMonth())));
            assertThat(b.getEndDate().getDayOfMonth(), is(equalTo(ZonedDateTime.now().plusDays(6).getDayOfMonth())));
            assertThat(b.getFrequencyType().getId(), is(equalTo(10)));
            assertThat(b.getFrequencyType().getFrequencyTypeName(), is(equalTo("TestFrequency")));
            assertThat(b.getAmount(), is(equalTo(BigDecimal.valueOf(54.21))));
            assertThat(b.getInUse(), is(false));

            BudgetCategory cat = b.getBudgetCategory();
            assertThat(cat, is(not(nullValue())));
            assertThat(cat.getId(), is(equalTo(5)));
            assertThat(cat.getType().getName(), is(equalTo("TestType")));
            assertThat(cat.getName().getName(), is(equalTo("TestCatName")));
            budgetsChecked++;
        }
        assertThat(budgetsChecked, is(equalTo(1)));
    }

    @Test
    void convertBudgetDtosToBudget_GivenCollectionNull_ThenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> BudgetUtil.convertBudgetDtosToBudget(null));
    }

    //    convertBudgetDtosToBudgetIncludeOnlyIdForChildEntity
    @Test
    void convertBudgetDtosToBudgetIncludeOnlyIdForChildEntity_GivenFullDtosProvided_ThenConvertToBudgetAndReturn() {

        // Arrange
        BudgetCategoryDto budgetCategoryDto = new BudgetCategoryDto(5, "TestType", "TestCatName");
        BudgetDto budgetDto = new BudgetDto(1L, "TestName", budgetCategoryDto, ZonedDateTime.now(),
                ZonedDateTime.now().plusDays(6), 10, "TestFrequency", BigDecimal.valueOf(54.21), false);

        // Act
        Set<Budget> budgets = BudgetUtil.convertBudgetDtosToBudgetIncludeOnlyIdForChildEntity(Collections.singletonList(budgetDto));

        // Assert
        assertThat(budgets, hasSize(1));
        int budgetsChecked = 0;
        for (Budget b : budgets) {
            assertThat(b.getId(), is(equalTo(1L)));
            assertThat(b.getName(), is(equalTo("TestName")));
            assertThat(b.getStartDate().getDayOfMonth(), is(equalTo(ZonedDateTime.now().getDayOfMonth())));
            assertThat(b.getEndDate().getDayOfMonth(), is(equalTo(ZonedDateTime.now().plusDays(6).getDayOfMonth())));
            assertThat(b.getFrequencyType().getId(), is(equalTo(10)));
            assertThat(b.getFrequencyType().getFrequencyTypeName(), is(nullValue()));
            assertThat(b.getAmount(), is(equalTo(BigDecimal.valueOf(54.21))));
            assertThat(b.getInUse(), is(false));

            BudgetCategory cat = b.getBudgetCategory();
            assertThat(cat, is(not(nullValue())));
            assertThat(cat.getId(), is(equalTo(5)));
            assertThat(cat.getType(), is(nullValue()));
            assertThat(cat.getName(), is(nullValue()));
            budgetsChecked++;
        }
        assertThat(budgetsChecked, is(equalTo(1)));
    }

    @Test
    void convertBudgetDtosToBudgetIncludeOnlyIdForChildEntity_GivenCollectionNull_ThenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> BudgetUtil.convertBudgetDtosToBudgetIncludeOnlyIdForChildEntity(null));
    }

    private Budget createBudget(String name, ZonedDateTime startDate, ZonedDateTime endDate, double amount, boolean inUse, long id) {
        BudgetCategoryType budgetCategoryType = new BudgetCategoryType(1, "TestBudgetCategoryType");

        BudgetCategoryName budgetCategoryName = new BudgetCategoryName(2, "TestBudgetCategoryName");


        BudgetCategory budgetCategory = new BudgetCategory(1, budgetCategoryType, budgetCategoryName, new HashSet<>());

        BudgetItem itemOne = new BudgetItem(6L, budgetCategory, "Item Name");

        BudgetItem itemTwo = new BudgetItem(7L, budgetCategory, "Item Name Two");

        itemOne.setBudgetCategory(budgetCategory);

        budgetCategory.getBudgetItems().add(itemOne);
        budgetCategory.getBudgetItems().add(itemTwo);

        FrequencyType frequencyType = new FrequencyType();
        frequencyType.setFrequencyTypeName("TestFrequencyTypeName");
        frequencyType.setId(3);

        return new Budget(
                id, budgetCategory, name, startDate,
                endDate, frequencyType, BigDecimal.valueOf(amount), inUse, 1, null);

    }

}
