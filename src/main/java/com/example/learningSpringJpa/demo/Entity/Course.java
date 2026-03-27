package com.example.learningSpringJpa.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Course {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @CreationTimestamp
    private LocalDateTime createdDateTime;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedDateTime;

    protected Course () {}
    public Course (String name) {
        this.name = name;
    }

    public Course(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }
}

/*
* Good question 👍 — these annotations are **used daily in real Spring Boot + JPA + Hibernate projects**, especially in enterprise applications (banking, fintech, CRM, SaaS systems).

Let’s understand them **clearly with real-world meaning + real project usage**.

---

# ✅ 1. `@Table` Annotation

## 📌 What it does

Defines **which database table** an entity maps to.

By default:

```
Entity name = Table name
```

But in real projects, DB table names are different.

---

## ✅ Syntax

```java
@Entity
@Table(name = "loan_application")
public class LoanApplication {
}
```

---

## 🧠 Real World Analogy

Think of:

* Entity → Java object (Employee form)
* Table → Physical file cabinet in office

`@Table` tells Hibernate:

👉 *“Store this object inside THIS cabinet.”*

---

## ✅ Real Project Use Cases

### 1️⃣ Database naming standards

Companies follow snake_case naming.

```sql
loan_application
user_account
payment_history
```

But Java uses camelCase.

```java
LoanApplication
UserAccount
PaymentHistory
```

So we map explicitly.

---

### 2️⃣ Schema usage (very common in enterprise)

```java
@Table(
    name = "users",
    schema = "auth"
)
```

Example:

* auth.users
* finance.transactions

Large systems separate schemas.

---

### 3️⃣ Unique constraints

```java
@Table(
   name="users",
   uniqueConstraints = @UniqueConstraint(columnNames="email")
)
```

Used for:

* email unique
* mobile number unique

---

---

# ✅ 2. `@Column` Annotation

## 📌 What it does

Defines **column properties** in database.

Without it:
Hibernate auto-generates columns.

With it:
👉 You control DB behavior.

---

## ✅ Syntax

```java
@Column(name = "user_name")
private String name;
```

---

## 🧠 Real World Meaning

You are telling DB:

> “This field should be saved in this specific column with these rules.”

---

## ✅ Common Properties (VERY IMPORTANT)

### ✔ Name mapping

```java
@Column(name="loan_amount")
```

---

### ✔ Not Null constraint

```java
@Column(nullable = false)
```

Real use:

* username
* password
* email

---

### ✔ Unique constraint

```java
@Column(unique = true)
private String email;
```

---

### ✔ Length control

```java
@Column(length = 50)
```

Prevents large unwanted data.

---

### ✔ Read-only field

```java
@Column(updatable = false)
```

Used for:

* createdDate
* createdBy

---

## ✅ Real Project Example

```java
@Column(name="status", length=20, nullable=false)
private String status;
```

Loan status:

* PENDING
* APPROVED
* REJECTED

---

---

# ✅ 3. `@CreationTimestamp`

(Hibernate Annotation)

## 📌 What it does

Automatically stores **record creation time**.

No manual coding needed.

---

## ✅ Syntax

```java
@CreationTimestamp
@Column(updatable = false)
private LocalDateTime createdAt;
```

---

## 🧠 Real World Analogy

Like:

> Office automatically stamping date when file is created.

You don't write code like:

```java
createdAt = LocalDateTime.now();
```

Hibernate handles it.

---

## ✅ Real Project Use Cases

Used in almost EVERY table:

* user created date
* order placed date
* loan application submitted date
* audit logs

---

## Example DB value

```
2026-03-26 10:45:12
```

---

---

# ✅ 4. `@UpdateTimestamp`

## 📌 What it does

Automatically updates timestamp whenever record changes.

---

## ✅ Syntax

```java
@UpdateTimestamp
private LocalDateTime updatedAt;
```

---

## 🧠 Real World Analogy

Whenever a file is edited → office updates “Last Modified Date”.

---

## ✅ Real Project Use Cases

Tracking:

* profile updates
* status change
* admin modifications
* payment updates

---

Example:

```
User edits profile → updatedAt changes automatically
```

---

---

# ✅ Combined Real Project Entity Example

This is how **industry-level entity looks** 👇

```java
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="user_name", nullable=false, length=50)
    private String name;

    @Column(unique=true, nullable=false)
    private String email;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
```

---

# ✅ What happens internally

### INSERT

```
createdAt → auto filled
updatedAt → auto filled
```

### UPDATE

```
createdAt → unchanged
updatedAt → new timestamp
```

---

# ✅ Why Companies Use These (Industry Reality)

In fintech / banking apps (like your KICredit platform):

Every record must track:

* Who created?
* When created?
* When modified?
* Audit history

These annotations remove **manual bugs**.

---

# ⭐ Senior Developer Tip (Important)

Almost all real projects create a **Base Entity**:

```java
@MappedSuperclass
public abstract class BaseEntity {

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
```

Then:

```java
public class LoanApplication extends BaseEntity {
}
```

✅ Cleaner code
✅ Reusable auditing

---

# 🚀 Industry Importance (Very High)

| Annotation           | Industry Usage |
| -------------------- | -------------- |
| `@Table`             | ⭐⭐⭐⭐⭐          |
| `@Column`            | ⭐⭐⭐⭐⭐          |
| `@CreationTimestamp` | ⭐⭐⭐⭐           |
| `@UpdateTimestamp`   | ⭐⭐⭐⭐           |

You will see them in:

* Spring Boot microservices
* Banking apps
* CRM systems
* E-commerce backend
* Audit systems

---

If you want next, I can explain **VERY IMPORTANT next topic** (used immediately after this in real projects):

👉 **`@EntityListeners` + Auditing (`createdBy`, `modifiedBy`) — how companies track WHICH USER updated data**.
*/