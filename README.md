
# Application Search Feature - README

A comprehensive search API for user applications with advanced filtering, pagination, sorting, and security features. Built with Spring Boot and JPA Specifications for flexible and efficient data retrieval.

## Overview

The search feature allows users to find applications using multiple criteria including national ID, names, Card Access Number (CAN), issue dates, and general text search. It supports both exact matches and partial searches with case-insensitive matching.

## Endpoint

### Search Applications

**GET** `/api/applications/search`

Search for applications using any combination of supported filters with pagination and sorting capabilities.

#### Query Parameters

| Parameter        | Type     | Required | Description                                                                                      |
|------------------|----------|----------|--------------------------------------------------------------------------------------------------|
| `nationalId`     | string   | No       | National ID - partial or full match, case-insensitive (max 255 chars)                      |
| `firstName`      | string   | No       | First name - partial or full match, case-insensitive (max 100 chars)                       |
| `lastName`       | string   | No       | Last name - partial or full match, case-insensitive (max 100 chars)                        |
| `issuedDateFrom` | date     | No       | Start of issued date range (YYYY-MM-DD format)                                              |
| `issuedDateTo`   | date     | No       | End of issued date range (YYYY-MM-DD format)                                                |
| `issueDate`      | date     | No       | Exact issue date (YYYY-MM-DD). Takes priority over date range                               |
| `can`            | string   | No       | Card Access Number - partial or full match (max 50 chars)                                   |
| `searchText`     | string   | No       | General search across all fields including date parsing (max 255 chars)                     |
| `page`           | integer  | No       | Page number, 1-based (default: 1, max: 1000)                                                |
| `size`           | integer  | No       | Page size (default: 10, max: 100)                                                           |
| `sortBy`         | string   | No       | Sort field: `issuedDate`, `firstName`, `lastName`, `nationalId`, `can` (default: `issuedDate`) |
| `sortOrder`      | string   | No       | Sort order: `asc` or `desc` (default: `desc`)                                               |

#### Example Requests

**Basic search by name:**
```

GET /api/applications/search?firstName=John\&lastName=Doe

```

**Date range search with sorting:**
```

GET /api/applications/search?issuedDateFrom=2024-01-01\&issuedDateTo=2024-12-31\&sortBy=lastName\&sortOrder=asc

```

**General text search:**
```

GET /api/applications/search?searchText=A123456789\&page=2\&size=5

```

**CAN search with pagination:**
```

GET /api/applications/search?can=CAN123\&page=1\&size=20

```

## Response Format

```

{
"page": 1,
"size": 10,
"totalResults": 25,
"totalPages": 3,
"applications": [
{
"nationalId": "A123456789",
"firstName": "John",
"lastName": "Doe",
"issuedDate": "2024-05-15",
"can": "CAN1234567"
}
],
"message": null
}

```

**Empty Result:**
```

{
"page": 1,
"size": 10,
"totalResults": 0,
"totalPages": 0,
"applications": [],
"message": "No records found"
}

```

## Search Features

### **Multi-Field Search**
- **Individual Fields:** Search by specific fields like nationalId, firstName, lastName, CAN[^3]
- **General Search:** Use `searchText` to search across all fields simultaneously[^3]
- **Date Search:** Support for exact date matching and date range filtering[^3]

### **CAN Search Logic**
- **Exact Match:** For 10-character alphanumeric CAN values[^3]
- **Partial Match:** For incomplete CAN searches[^3]
- **Case-Insensitive:** All CAN searches ignore case[^3]

### **Date Handling**
- **Exact Date:** Use `issueDate` for precise date matching[^3]
- **Date Range:** Use `issuedDateFrom` and `issuedDateTo` for range filtering[^3]
- **Smart Parsing:** `searchText` can parse and match date strings in YYYY-MM-DD format[^3]

### **Input Sanitization**
- SQL injection prevention through input sanitization[^3]
- Escaping of SQL wildcards (%, _, \)[^3]
- Removal of special characters except alphanumeric, spaces, and hyphens[^3]

## Security & Performance

### **Authentication & Authorization**
- **JWT Authentication:** Requires valid Bearer token[^9]
- **Role-Based Access:** Only users with required admin roles can access[^22]
- **Rate Limiting:** 100 requests per minute per IP address[^8]

### **Performance Features**
- **Pagination:** Efficient data retrieval with configurable page sizes[^3]
- **Sorting:** Database-level sorting for optimal performance[^3]
- **JPA Specifications:** Dynamic query building for complex searches[^3]
- **Transaction Management:** Read-only transactions for search operations[^3]

## Error Handling

| Status Code | Description                                    |
|-------------|------------------------------------------------|
| 200         | Success - Results returned                     |
| 400         | Bad Request - Invalid parameters or validation |
| 401         | Unauthorized - Missing or invalid token        |
| 403         | Forbidden - Insufficient permissions           |
| 429         | Too Many Requests - Rate limit exceeded        |
| 500         | Internal Server Error - Unexpected error       |

**Error Response Format:**
```

{
"status": 400,
"message": "Validation Error",
"timestamp": "2024-07-03T15:30:00",
"errors": {
"firstName": "First name cannot exceed 100 characters"
}
}

```

## Implementation Details

### **Core Components**
- **Controller:** `ApplicationSearchController` - REST endpoint handling[^18]
- **Service:** `ApplicationSearchServiceImpl` - Business logic and search specifications[^3]
- **Repository:** `UserInfoRepository` - Data access with JPA Specifications[^4]
- **DTOs:** `ApplicationSearchResponseDTO`, `ApplicationSearchResultDTO` - Data transfer objects[^15][^16]

### **Database Mapping**
The search operates on the `UserInfo` entity with the following field mappings:
- `nationalId` → `nationalUid`
- `firstName` → `firstNamePrimary`
- `lastName` → `lastNameSecondary`
- `issuedDate` → `createdTimes`
- `can` → `cardAccessNumber`

### **Validation Rules**
- All string parameters have maximum length constraints[^18]
- Page numbers must be between 1 and 1000[^18]
- Page size must be between 1 and 100[^18]
- Sort fields are restricted to predefined values[^18]
- Sort order must be either 'asc' or 'desc'[^18]

## Usage Examples

### **Search by Multiple Criteria**
```

curl -X GET "http://localhost:8080/api/applications/search?firstName=Jane\&can=CAN123\&sortBy=issuedDate\&sortOrder=desc" \
-H "Authorization: Bearer YOUR_JWT_TOKEN"

```

### **General Text Search**
```

curl -X GET "http://localhost:8080/api/applications/search?searchText=2024-05-15\&page=1\&size=5" \
-H "Authorization: Bearer YOUR_JWT_TOKEN"

```

### **Date Range Search**
```

curl -X GET "http://localhost:8080/api/applications/search?issuedDateFrom=2024-01-01\&issuedDateTo=2024-06-30" \
-H "Authorization: Bearer YOUR_JWT_TOKEN"

```

## Notes

- The search feature is optimized for performance with database-level filtering and sorting.
- All searches are case-insensitive for better user experience.
- The `searchText` parameter provides a convenient way to search across all fields.
- Rate limiting prevents abuse while allowing reasonable usage patterns.
- Comprehensive validation ensures data integrity and security.
