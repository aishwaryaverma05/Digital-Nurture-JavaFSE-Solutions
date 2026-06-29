SET SERVEROUTPUT ON;

BEGIN
    FOR txn_rec IN (
        SELECT c.Name, t.TransactionID, t.TransactionDate, t.Amount, t.TransactionType
        FROM Customers c
        JOIN Accounts a ON c.CustomerID = a.CustomerID
        JOIN Transactions t ON a.AccountID = t.AccountID
        WHERE EXTRACT(MONTH FROM t.TransactionDate) = EXTRACT(MONTH FROM SYSDATE)
        AND EXTRACT(YEAR FROM t.TransactionDate) = EXTRACT(YEAR FROM SYSDATE)
    )
    LOOP
        DBMS_OUTPUT.PUT_LINE(
            'Customer: ' || txn_rec.Name ||
            ', Transaction ID: ' || txn_rec.TransactionID ||
            ', Type: ' || txn_rec.TransactionType ||
            ', Amount: ' || txn_rec.Amount
        );
    END LOOP;
END;
/





BEGIN
    FOR acc_rec IN (
        SELECT AccountID, Balance
        FROM Accounts
    )
    LOOP
        UPDATE Accounts
        SET Balance = Balance - 100
        WHERE AccountID = acc_rec.AccountID;

        DBMS_OUTPUT.PUT_LINE('Annual fee applied to Account ID: ' || acc_rec.AccountID);
    END LOOP;

    COMMIT;
END;
/


SELECT AccountID, AccountType, Balance
FROM Accounts;



BEGIN
    FOR loan_rec IN (
        SELECT LoanID, InterestRate
        FROM Loans
    )
    LOOP
        UPDATE Loans
        SET InterestRate = InterestRate + 0.5
        WHERE LoanID = loan_rec.LoanID;

        DBMS_OUTPUT.PUT_LINE(
            'Updated Interest Rate for Loan ID: ' || loan_rec.LoanID
        );
    END LOOP;

    COMMIT;
END;
/



SELECT LoanID, LoanAmount, InterestRate
FROM Loans;




CREATE OR REPLACE PACKAGE CustomerManagement AS
    PROCEDURE AddCustomer(
        p_customer_id NUMBER,
        p_name VARCHAR2,
        p_dob DATE,
        p_balance NUMBER
    );

    PROCEDURE UpdateCustomer(
        p_customer_id NUMBER,
        p_name VARCHAR2,
        p_balance NUMBER
    );

    FUNCTION GetCustomerBalance(
        p_customer_id NUMBER
    ) RETURN NUMBER;
END CustomerManagement;
/



CREATE OR REPLACE PACKAGE BODY CustomerManagement AS

    PROCEDURE AddCustomer(
        p_customer_id NUMBER,
        p_name VARCHAR2,
        p_dob DATE,
        p_balance NUMBER
    )
    AS
    BEGIN
        INSERT INTO Customers
        (CustomerID, Name, DOB, Balance, LastModified, IsVIP)
        VALUES
        (p_customer_id, p_name, p_dob, p_balance, SYSDATE, 'FALSE');

        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Customer added successfully.');
    END;

    PROCEDURE UpdateCustomer(
        p_customer_id NUMBER,
        p_name VARCHAR2,
        p_balance NUMBER
    )
    AS
    BEGIN
        UPDATE Customers
        SET Name = p_name,
            Balance = p_balance,
            LastModified = SYSDATE
        WHERE CustomerID = p_customer_id;

        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Customer updated successfully.');
    END;

    FUNCTION GetCustomerBalance(
        p_customer_id NUMBER
    ) RETURN NUMBER
    AS
        v_balance NUMBER;
    BEGIN
        SELECT Balance
        INTO v_balance
        FROM Customers
        WHERE CustomerID = p_customer_id;

        RETURN v_balance;
    END;

END CustomerManagement;
/




BEGIN
    CustomerManagement.AddCustomer(
        4,
        'Neha Verma',
        TO_DATE('1998-04-12','YYYY-MM-DD'),
        7000
    );
END;
/



SELECT CustomerManagement.GetCustomerBalance(4) AS Balance
FROM dual;



CREATE OR REPLACE PACKAGE EmployeeManagement AS

    PROCEDURE HireEmployee(
        p_employee_id NUMBER,
        p_name VARCHAR2,
        p_position VARCHAR2,
        p_salary NUMBER,
        p_department VARCHAR2,
        p_hiredate DATE
    );

    PROCEDURE UpdateEmployee(
        p_employee_id NUMBER,
        p_salary NUMBER
    );

    FUNCTION CalculateAnnualSalary(
        p_employee_id NUMBER
    ) RETURN NUMBER;

END EmployeeManagement;
/




CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS

    PROCEDURE HireEmployee(
        p_employee_id NUMBER,
        p_name VARCHAR2,
        p_position VARCHAR2,
        p_salary NUMBER,
        p_department VARCHAR2,
        p_hiredate DATE
    )
    AS
    BEGIN
        INSERT INTO Employees
        VALUES(
            p_employee_id,
            p_name,
            p_position,
            p_salary,
            p_department,
            p_hiredate
        );

        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Employee hired successfully.');
    END;

    PROCEDURE UpdateEmployee(
        p_employee_id NUMBER,
        p_salary NUMBER
    )
    AS
    BEGIN
        UPDATE Employees
        SET Salary = p_salary
        WHERE EmployeeID = p_employee_id;

        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Employee updated successfully.');
    END;

    FUNCTION CalculateAnnualSalary(
        p_employee_id NUMBER
    )
    RETURN NUMBER
    AS
        v_salary NUMBER;
    BEGIN
        SELECT Salary
        INTO v_salary
        FROM Employees
        WHERE EmployeeID = p_employee_id;

        RETURN v_salary * 12;
    END;

END EmployeeManagement;
/



BEGIN
    EmployeeManagement.HireEmployee(
        3,
        'Rohit Sharma',
        'Analyst',
        50000,
        'Finance',
        SYSDATE
    );
END;
/


SELECT EmployeeManagement.CalculateAnnualSalary(3)
FROM dual;





CREATE OR REPLACE PACKAGE AccountOperations AS
    PROCEDURE OpenAccount(
        p_account_id NUMBER,
        p_customer_id NUMBER,
        p_account_type VARCHAR2,
        p_balance NUMBER
    );

    PROCEDURE CloseAccount(
        p_account_id NUMBER
    );

    FUNCTION GetTotalBalance(
        p_customer_id NUMBER
    ) RETURN NUMBER;
END AccountOperations;
/





CREATE OR REPLACE PACKAGE BODY AccountOperations AS

    PROCEDURE OpenAccount(
        p_account_id NUMBER,
        p_customer_id NUMBER,
        p_account_type VARCHAR2,
        p_balance NUMBER
    )
    AS
    BEGIN
        INSERT INTO Accounts
        VALUES(p_account_id, p_customer_id, p_account_type, p_balance, SYSDATE);

        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Account opened successfully.');
    END;

    PROCEDURE CloseAccount(
        p_account_id NUMBER
    )
    AS
    BEGIN
        DELETE FROM Accounts
        WHERE AccountID = p_account_id;

        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Account closed successfully.');
    END;

    FUNCTION GetTotalBalance(
        p_customer_id NUMBER
    )
    RETURN NUMBER
    AS
        v_total NUMBER;
    BEGIN
        SELECT SUM(Balance)
        INTO v_total
        FROM Accounts
        WHERE CustomerID = p_customer_id;

        RETURN v_total;
    END;

END AccountOperations;
/





BEGIN
    AccountOperations.OpenAccount(3, 1, 'Savings', 2000);
END;
/



SELECT AccountOperations.GetTotalBalance(1) AS Total_Balance
FROM dual;