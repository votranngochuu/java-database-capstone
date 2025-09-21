# Smart Clinic – User Stories

> Các user stories được viết theo format **As a / I want / So that** và có **Acceptance Criteria**, **Priority**, **Story Points**.  
> Các story bao phủ 3 vai trò: **Doctor**, **Patient**, **Admin**.

---

## DOCTOR

### US-D1: Manage available time slots
- **As a** Doctor  
- **I want** to add, edit, and delete my available appointment time slots  
- **So that** patients can only book the times when I’m free  

**Acceptance Criteria**
1. Doctor can create, update, or remove a time slot (date + start time).
2. Overlapping or duplicated time slots are rejected.
3. Patients only see non-booked, valid time slots.
4. All changes are persisted in the database and visible immediately.

**Priority:** High  
**Story Points:** 5

---

### US-D2: View daily appointments
- **As a** Doctor  
- **I want** to view all appointments for a selected day  
- **So that** I can prepare and manage my schedule effectively  

**Acceptance Criteria**
1. Doctor selects a date and sees a list of appointments for that date.
2. Each item shows patient name (masked if required), time, and reason/notes.
3. Canceled appointments are clearly marked and excluded from free slots.
4. Results are sorted by time.

**Priority:** Medium  
**Story Points:** 3

---

## PATIENT

### US-P1: Search for doctors by specialty and name
- **As a** Patient  
- **I want** to search doctors by specialty and/or name  
- **So that** I can quickly find a suitable doctor  

**Acceptance Criteria**
1. Search supports specialty (e.g., Cardiology) and partial name.
2. Results show doctor name, specialty, rating (if available), and clinic location.
3. Pagination is available for large result sets.
4. Search is case-insensitive.

**Priority:** High  
**Story Points:** 5

---

### US-P2: Book an appointment
- **As a** Patient  
- **I want** to book an appointment in one of a doctor’s available time slots  
- **So that** I can reserve a visit without calling the clinic  

**Acceptance Criteria**
1. Patient picks a doctor and a visible available time slot.
2. System validates that the slot is still free at the moment of booking.
3. On success, the slot becomes unavailable to others and a confirmation is returned.
4. Booking fails with a clear message if the slot was taken concurrently.

**Priority:** High  
**Story Points:** 8

---

## ADMIN

### US-A1: Manage doctor profiles
- **As an** Admin  
- **I want** to create, update, and deactivate doctor profiles  
- **So that** the directory of available doctors is always accurate  

**Acceptance Criteria**
1. Admin can add a doctor with name, email (unique), phone, and specialty.
2. Admin can update any field or deactivate a doctor (soft delete).
3. Email uniqueness is enforced by validation.
4. Changes are audit-logged (who, when, action).

**Priority:** High  
**Story Points:** 5

---

### US-A2: View system usage reports
- **As an** Admin  
- **I want** to view monthly reports of appointments per doctor  
- **So that** I can monitor demand and plan staffing  

**Acceptance Criteria**
1. Report shows number of patients seen per doctor per month.
2. Time range filter and export to CSV are available.
3. The data source is the production database or a reporting view.
4. Large datasets return in < 5s for the past 12 months.

**Priority:** Medium  
**Story Points:** 3
