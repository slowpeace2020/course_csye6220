<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>HW1_part4</title>
    <style>
        input[type="underline"] {
            border-bottom: 1px solid #000000;
            border-top: 0px;
            border-left: 0px;
            border-right: 0px;
        }
    </style>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<form action="${pageContext.request.contextPath}/part6" method="post">
    <div style="float: left">
        <h3 style="color: crimson">Section 1</h3>
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    Academic Term: <input type="underline" placeholder="" name="Academic Term" value="">&nbsp;&nbsp;
                    <br/>
                    <label>(Fall, Winter, Spring, or Summer)* </label>
                </div>
                <div class="col-lg-3">
                    Academic Year: <input type="underline" name="Academic Year">&nbsp;</br>
                </div>
                <div class="col-lg-3">
                    Employee Status:
                    <input type="checkbox" name="status-type" value="P/T Staff"/>P/T Staff
                    <input type="checkbox" name="status-type" value="P/T Faculty"/>P/T Faculty
                    <input type="checkbox" name="status-type" value="Retiree"/>Retiree
                    <input type="checkbox" name="status-type" value="Other"/>Other<input type="underline">
                </div>
            </div>
        </div>
        <div style="margin-top: 10px">
            <span>* </span>
            <label tabindex="-1">Terms with multiple sessions are considered one academic term for tuition waiver purposes
                e.g., summer sessions I and II are considered one academic term</label>
        </div>
    </div>
    <div style="float: left">
        <h3 style="color: crimson">Section 2</h3>
        <div class="container">
            <div class="row" style="display: inline">
                <div class="col-md-9" style="float: left;width: 400px">
                    <input type="underline" ><input type="underline" name="Student's Name"></br>
                    Student's Name &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Relationship to Employee &nbsp;&nbsp;
                </div>
                <div class="col-md-3" style="float: inside; margin-left: 10px">
                    <input type="underline" name="Student's NUID"></br>
                    Student's NUID
                </div>
            </div>
            <div class="row" style="display: inline">
                <div class="col-md-9" style="float: left;width: 400px">
                    <input type="underline" name="Employee's Name" size="40"></br>
                    Employee's Name (if different from Student's) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
                <div class="col-md-3" style="float: inside; margin-left: 10px">
                    <input type="underline" name="Employee's NUID"></br>
                    Employee's NUID
                </div>
            </div>
            <div class="row" style="display: inline">
                <div class="col-md-9" style="float: left;width: 400px">
                    <input type="underline" size="10" name="Department">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="underline" name="Campus Location" size="15"> &nbsp;&nbsp;&nbsp;&nbsp;<input type="underline" size="12" name="Phone Number"></br>
                    Department &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Campus Location &nbsp;&nbsp;   &nbsp;&nbsp;  Phone Number
                </div>
                <div class="col-md-6" style="float: inside; margin-left: 10px">
                    <input type="underline" name="Supervisor's Name"></br>
                    Supervisor's Name
                </div>
            </div>
        </div>
    </div>
    <br>
    <br>
    <div style="float: left">
        <div>
            <h3 style="color: crimson">Section 3</h3>
            <div class="container">
                <div style="margin-bottom: 10px; width: 70%" >
                    <label tabindex="-1">* In accordance with Northeastern University's Tuition Waiver Program Guidelines, my signature attests that I am
                        currently enrolled in the above courses, or that the student listed above is my spouse, domestic partner or
                        unmarried dependent child or the dependent child of my domestic partner as defined under current IRS tax
                        regulations.  I also certify that I have read the Tuition Waiver Program Guidelines.</label>
                </div>
                <div style="margin-bottom: 10px; width: 70%; background-color: #858585" >
                    <b>
                        One course per academic term may be taken one-half hour before the end of your workday.  If this applies to you, this form must be signed by your supervisor below and approved by Human Resources Management.
                    </b>
                </div>
                <div style="margin-bottom: 10px; width: 70%; background-color: #858585" >
                    <b>
                        Complete the following course information.  If you are an employee taking a job related course, please attach the Job Related Designation Form to this waiver.
                    </b>
                </div>
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Course No.</th>
                            <th>Course Name</th>
                            <th>Supervisor Signature </th>
                            <th>Credit Hrs </th>
                            <th>Day(s) </th>
                            <th>Time</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><input name="courseId[]" value=""></td>
                            <td><input name="courseName[]" value=""></td>
                            <td><input name="supervisorSignature[]" value=""></td>
                            <td><input name="creditHours[]" value=""></td>
                            <td><input name="days[]" value=""></td>
                            <td><input name="time[]" value=""><strong >&nbsp;a.m./p.m.</strong></td>
                        </tr>
                        <tr>
                            <td><input name="courseId[]" value=""></td>
                            <td><input name="courseName[]" value=""></td>
                            <td><input name="supervisorSignature[]" value=""></td>
                            <td><input name="creditHours[]" value=""></td>
                            <td><input name="days[]" value=""></td>
                            <td><input name="time[]" value=""><strong >&nbsp;a.m./p.m.</strong></td>
                        </tr>
                        <tr>
                            <td><input name="courseId[]" value=""></td>
                            <td><input name="courseName[]" value=""></td>
                            <td><input name="supervisorSignature[]" value=""></td>
                            <td><input name="creditHours[]" value=""></td>
                            <td><input name="days[]" value=""></td>
                            <td><input name="time[]" value=""><strong >&nbsp;a.m./p.m.</strong></td>
                        </tr>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
        <div>
            <h3 style="color: crimson">Section 4</h3>
            <div class="container">
                <div class="row" style="display: inline">
                    <div class="col-md-9" style="float: left;width: 400px">
                        <input type="underline" name="Employee's Signature"></br>
                        Employee's Signature
                    </div>
                    <div class="col-md-3" style="float: inside; margin-left: 10px">
                        <input type="underline" name="Signature Date"></br>
                        Date
                    </div>
                </div>
            </div>
        </div>
        <div>
            <h3 style="color: crimson">Section 5</h3>
            <div class="container">
                <div class="row" style="display: inline">
                    <div class="col-md-9" style="float: left;width: 400px">
                        <input type="underline" name="HRM Approval"></br>
                        HRM Approval
                    </div>
                    <div class="col-md-3" style="float: inside; margin-left: 10px">
                        <input type="underline" name="HRM Approval Date"></br>
                        Date
                    </div>
                </div>
            </div>
        </div>
        <div>
            <div class="container">
                <div class="row" style="display: inline">
                    <div class="col-md-9" style="float: left;width: 400px">
                        <tr>
                            <td align="right"></td>
                            <td>
                                <input type="submit" value="Submit">
                            </td>
                        </tr>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>