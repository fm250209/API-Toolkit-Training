01  MAILING-RECORD.
 05  COMPANY-NAME            PIC X(30).
 05  CONTACTS.
     10  PRESIDENT.
         15  LAST-NAME       PIC X(15) VALUE 'INDIA'.
         15  FIRST-NAME      PIC X(8).
         15  AMT             PIC 999V99.
         15  AMT2            PIC 9(3).9(2).
         15  AMT3            PIC 9(3).99.
         15  AMT4            PIC -999.9(2).
         15  ZIP-CODE        PIC +99999.
         15  MIDDLE-NAME     PIC A(15).
     10  VP-MARKETING.
         15  LAST-NAME       PIC X(15).
         15  FIRST-NAME      PIC X(8).
         15  ZIP-PLUS-9      PIC 99999-9999.
     10  ALTERNATE-CONTACT.
         15  TITLE           PIC X(10).
         15  LAST-NAME       PIC X(15).
         15  FIRST-NAME      PIC X(8).
         *THIS IS A TEST COMMENT
         15  AMOUNT          PIC 999.99.
 05  ADDRESS                 PIC X(15).
 05  CITY                    PIC X(15) VALUE 'THIS IS A STRING'.
 05  STATE                   PIC XX.
     88  VALID   VALUES 'ko', 'oh', 'hi'.
 05  NUM                     PIC 9.88  PRE-SCHOOL   VALUE 0 THROUGH 4.
 05  SIGNED                  PIC S9(3)V9(2).
 05  ZIP                     PIC 9(5).
     88  WS-INVALID-STATUS   VALUES +06 , +07.
 05  ZIP-PLUS-92             PIC 99999X9999.
 05  AME                     PIC XXXXXXXXXXXXXXX.
 05  SEX                     PIC X.
     88  MALE     VALUE "M".
     88  FEMALE   VALUE "F".
     88  OTHER    VALUES '0' THRU '9' ' ' '@' '$' '%'.
 6   PADTEST1                PIC 999PPP.
 07  PADTEST2                PIC PPP999.
 08  ACCOUNT-BALANCE         PIC S9(6)V99 USAGE IS COMPUTATIONAL-3.
 09  ACCOUNT-BALANCE2        PIC S9(5)V99 COMP-3.
 09  FILLER                  PIC X(10).