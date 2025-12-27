students = [
    {"id": 1, "name": "Aniket", "age": 18, "course": "AI"},
    {"id": 2, "name": "Rahul", "age": 19, "course": "CSE"}
]

students = []

def add_student():
    student_id = int(input("Enter Student ID: "))
    name = input("Enter Name: ")
    age = int(input("Enter Age: "))
    course = input("Enter Course: ")

    student = {
        "id": student_id,
        "name": name,
        "age": age,
        "course": course
    }

    students.append(student)
    print("✅ Student added successfully!")

def view_students():
    if not students:
        print("❌ No students found.")
        return

    for student in students:
        print(student)

def search_student():
    search_id = int(input("Enter Student ID to search: "))

    for student in students:
        if student["id"] == search_id:
            print("✅ Student found:")
            print(student)
            return

    print("❌ Student not found.")

def update_student():
    update_id = int(input("Enter Student ID to update: "))

    for student in students:
        if student["id"] == update_id:
            student["name"] = input("Enter new name: ")
            student["age"] = int(input("Enter new age: "))
            student["course"] = input("Enter new course: ")

            print("✅ Student updated successfully!")
            return

    print("❌ Student not found.")

def delete_student():
    delete_id = int(input("Enter Student ID to delete: "))

    for student in students:
        if student["id"] == delete_id:
            students.remove(student)
            print("🗑 Student deleted successfully!")
            return

    print ("❌ Student not found.")

def main_menu():
    while True:
        print("\n--- Student Management System ---")
        print("1. Add Student")
        print("2. View Students")
        print("3. Search Student")
        print("4. Update Student")
        print("5. Delete Student")
        print("6. Exit")

        choice = input("Enter your choice: ")

        if choice == "1":
            add_student()
        elif choice == "2":
            view_students()
        elif choice == "3":
            search_student()
        elif choice == "4":
            update_student()
        elif choice == "5":
            delete_student()
        elif choice == "6":
            print("👋 Exiting program...")
            break
        else:
            print("❌ Invalid choice!")


main_menu()

def search_student():
    search_id = int(input("Enter Student ID to search: "))

    for student in students:
        if student["id"] == search_id:
            print("✅ Student found:")
            print(student)
            return

    print("❌ Student not found.")


