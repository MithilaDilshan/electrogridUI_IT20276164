<%@page import="com.BillManage.Bill"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Billing Management</title>
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<script src="Components/jquery.min.js"></script>
		<script src="Components/bill.js"></script>
	</head>
	<style>
   body {
    	background-image: url("image/Billing.jpg");
	}
</style>
	<body > 
		<div class="container"><div class="row"><div class="col-6"> <br>
		<div style = "background-color:white; width:350px"><br><h2 style = "color:blue">&nbsp; Billing Management </h2>
		
			<form id="formBill" name="formBill" >
			
 				&nbsp; Bill AccountNo : 
 				<input id="bill_AccNo" name="bill_AccNo" type="text" placeholder="Enter Account No"
 				class="form-control form-control-sm" style="width:200px;margin-left:20px"> <br>
			    
			    &nbsp; Bill Date : 
 				<input id="bill_Date" name="bill_Date" type="text" placeholder="Enter Date"
 				class="form-control form-control-sm" style="width:200px;margin-left:20px"><br> 
 				
 				&nbsp; Bill UnitAmount : 
 				<input id="bill_UnitA" name="bill_UnitA" type="text" placeholder="Enter Unit Amount"
				class="form-control form-control-sm" style="width:200px;margin-left:20px"><br>
 				
 				&nbsp; Bill UnitPrice : 
 				<input id="bill_Unitprice" name="bill_Unitprice" type="text" placeholder="Enter Unit Price"
 				class="form-control form-control-sm" style="width:200px;margin-left:20px"><br> 
 				
 				&nbsp; Bill Total : 
 				<input id="bill_Total" name="bill_Total" type="text" placeholder="Enter Total Price"
 				class="form-control form-control-sm" style="width:200px;margin-left:20px"><br>
 				
 				<input id="btnSave" name="btnSave" type="button" value="Save" 
 				class="btn btn-primary" style="margin-left:150px">
 				<input type="hidden" id="hidBillIDSave" 
				name="hidBillIDSave" value="">
				
			</form><br></div>
			
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
		<br>
		
		<div id="divBillGrid">
 		<%
 			Bill billObj = new Bill(); 
 		 		out.print(billObj.readBill());
 		%>
	</div>
	
	</div> </div> </div> 
</body><br>
</html>
