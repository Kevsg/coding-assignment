## API Design

The solution I came up with is similar to SQL where we use SELECT, WHERE, SORT to query for the data to satisy the requirement.

------------------------------------------------------------------------------------------

## <summary><code>GET /job-data</code>

##### Parameters

> | name   | type     | Syntax                          | description                                                                                                 |
> |--------|----------|---------------------------------|-------------------------------------------------------------------------------------------------------------|
> | select | optional | { COLUMN }                      | Select column that you want from the database. Able to chain multiple column by using \| eg. salary\|gender |
> | where  | optional | { COLUMN \| OPERATOR  \| VALUE} | Filter row where a column satisfy a condition. Able to chain multiple comparison.                           |
> | sort   | optional | { COLUMN \| ORDER_TYPE  }       | Sort result by column both ascending and descending.                                                        |


### Avalable value for each Variable

### COLUMN 
- timestamp
- employer
- location
- jobTitle
- yearsAtEmployer
- yearsOfExperience
- salary
- signBonus
- annualBonus
- annualStockValue
- gender
- comments

### OPERATOR
- eq (Equal) 
- gte (Greater than or equal)
- gt (Greater than)
- lt (less than)
- lte (less than or equal)
- neq (Not equal) 

### VALUE (Can be any string that can should be use for compare)

### ORDER_TYPE
- ASC (Ascending)
- DESC (Descending)

##### Responses

> | http code | content-type                      | response                         |
> |-----------|-----------------------------------|----------------------------------|
> | `200`     | `application/json`        | `[...]`                          |
> | `400`     | `application/json`                | `{"message": "{$errorMessage}"}` |
                                                              |

##### Example cURL

> ```javascript
>  curl -X GET 'localhost:8080/job_data?select=timestamp|employer|location|jobTitle|yearsAtEmployer|yearsOfExperience|salary|signBonus|annualBonus|annualStockValue|gender|comments&where=salary|gt|10|gender|eq|Male&sort=jobTitle|ASC|salary|DESC'
> ```


------------------------------------------------------------------------------------------
