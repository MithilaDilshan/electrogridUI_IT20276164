$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	 {
	 	$("#alertSuccess").hide();
	 }
	 	$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	 $("#alertSuccess").text("");
	 $("#alertSuccess").hide();
	 $("#alertError").text("");
	 $("#alertError").hide();

// Form validation-------------------
var status = validateBillForm();
	if (status != true)
	 {
		 $("#alertError").text(status);
		 $("#alertError").show();
		 return;
     }
 
// If valid------------------------
var type = ($("#hidBillIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "BillAPI",
 type : type,
 data : $("#formBill").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onBillSaveComplete(response.responseText, status);
 }
 });
});

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidBillIDSave").val($(this).data("billid"));
 $("#bill_AccNo").val($(this).closest("tr").find('td:eq(0)').text());
 $("#bill_Date").val($(this).closest("tr").find('td:eq(1)').text());
 $("#bill_UnitA").val($(this).closest("tr").find('td:eq(2)').text());
 $("#bill_Unitprice").val($(this).closest("tr").find('td:eq(3)').text());
 $("#bill_Total").val($(this).closest("tr").find('td:eq(4)').text());
});

//DELETE==========================================================
$(document).on("click", ".btnRemove", function(event)
{
 $.ajax(
 {
 url : "BillAPI",
 type : "DELETE",
 data : "biil_ID=" + $(this).data("billid"),
 dataType : "text",
 complete : function(response, status)
 {
 onBillDeleteComplete(response.responseText, status);
 }
 });
});

// CLIENT-MODEL================================================================
function validateBillForm()
{
	
//BillAccNo
if ($("#bill_AccNo").val().trim() == "")
 {
 return "Insert bill_AccNo.";
 }

// BillDate
if ($("#bill_Date").val().trim() == "")
 {
 return "Insert bill_Date.";
 } 

// BillUnitAmount-------------------------------
if ($("#bill_UnitA").val().trim() == "")
 {
 return "Insert bill_UnitA.";
 }

// BillUnitPrice-------------------------------
if ($("#bill_Unitprice").val().trim() == "")
 {
 return "Insert bill_Unitprice.";
 }
  
 // BillTotal-------------------------------
if ($("#bill_Total").val().trim() == "")
 {
 return "Insert bill_Total.";
 }
 
return true;
}

function onBillSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divBillGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 } 
$("#hidBillIDSave").val("");
 $("#formBill")[0].reset();
}

function onBillDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divBillGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}
