# MySQL Schema Design - Smart Clinic Management System

## Tables

### 1. Doctor
| Field Name      | Data Type      | Constraints              |
|-----------------|---------------|--------------------------|
| doctor_id       | INT           | PRIMARY KEY, AUTO_INCREMENT |
| name            | VARCHAR(100)  | NOT NULL                 |
| email           | VARCHAR(100)  | UNIQUE, NOT NULL         |
| specialization  | VARCHAR(100)  | NOT NULL                 |
| phone           | VARCHAR(20)   |                          |

---

### 2. Patient
| Field Name      | Data Type      | Constraints              |
|-----------------|---------------|--------------------------|
| patient_id      | INT           | PRIMARY KEY, AUTO_INCREMENT |
| name            | VARCHAR(100)  | NOT NULL                 |
| email           | VARCHAR(100)  | UNIQUE, NOT NULL         |
| phone           | VARCHAR(20)   |                          |
| date_of_birth   | DATE          |                          |

---

### 3. Appointment
| Field Name      | Data Type      | Constraints              |
|-----------------|---------------|--------------------------|
| appointment_id  | INT           | PRIMARY KEY, AUTO_INCREMENT |
| doctor_id       | INT           | FOREIGN KEY REFERENCES Doctor(doctor_id) |
| patient_id      | INT           | FOREIGN KEY REFERENCES Patient(patient_id) |
| appointment_time| DATETIME      | NOT NULL                 |
| status          | VARCHAR(20)   | DEFAULT 'SCHEDULED'      |

---

### 4. Prescription
| Field Name      | Data Type      | Constraints              |
|-----------------|---------------|--------------------------|
| prescription_id | INT           | PRIMARY KEY, AUTO_INCREMENT |
| appointment_id  | INT           | FOREIGN KEY REFERENCES Appointment(appointment_id) |
| medication      | VARCHAR(255)  | NOT NULL                 |
| dosage          | VARCHAR(100)  | NOT NULL                 |
| instructions    | TEXT          |                          |

---

## Relationships
- One **Doctor** can have many **Appointments**.  
- One **Patient** can have many **Appointments**.  
- One **Appointment** can have one **Prescription**.  
