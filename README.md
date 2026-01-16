# Rates Server

Backend server for fixed income portfolio management and analytics.

## Requirements

- Java 21+
- Maven 3.9+

## Building

```bash
./mvnw clean install
```

## Running

```bash
./mvnw spring-boot:run -pl runtime
```

The server will start at `http://localhost:8080`.

## API Endpoints

### Root
- `GET /` - Returns server name

### Bond Analytics
- `POST /api/v1/bond-analytics/accrued-interest` - Calculate accrued interest for a bond

Example request:
```json
{
  "faceValue": 1000.0,
  "couponRate": 0.05,
  "lastCouponDate": "2024-06-15",
  "settlementDate": "2024-09-15",
  "dayCountConvention": "ACT/365"
}
```

Supported day count conventions: `30/360`, `ACT/360`, `ACT/365`, `ACT/ACT`
