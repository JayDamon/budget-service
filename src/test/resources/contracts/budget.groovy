package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url $(consumer(regex('^/v1/budgets/[1-9][0-9]?$|^100$')), producer('/v1/budgets/1'))
    }
    response {
        status 200
        body(
                id: $(anyNumber()),
                name: $(anyNonBlankString()),
                budgetCategory: [
                        id  : $(anyNumber()),
                        type: $(anyNonBlankString()),
                        name: $(anyNonBlankString())
                ],
                startDate: $(anyIso8601WithOffset()),
                frequencyTypeId: $(anyNumber()),
                frequencyType: $(anyNonBlankString()),
                amount: $(anyDouble()),
                inUse: $(anyBoolean())
        )
        headers {
            contentType('application/json')
        }
    }
}
