let update = false;

const itemForm = document.getElementById("itemForm");
const itemIdInput = document.getElementById("itemId");
const formTitle = document.getElementById("formTitle");

itemForm.addEventListener("submit", onSubmit);

function onSubmit(e) {
  e.preventDefault();

  const item = getItemFromForm();

  if (update) sendPutRequest(item);
  else sendPostRequest(item);
}

async function sendPostRequest(item) {
  try {
    await axios.post(`/api/${path}`, item);
    alert("Item added");
    window.location.reload();
  } catch (err) {
    console.error(err);
  }
}

async function sendPutRequest(item) {
  try {
    await axios.put(`/api/${path}/${item.id}`, _omit(item, "id"));
    alert("Item updated");
    window.location.reload();
  } catch (err) {
    console.error(err);
  }
}

async function sendDeleteRequest(itemId) {
  try {
    await axios.delete(`/api/${path}/${itemId}`);
    alert("Item deleted");
    window.location.reload();
  } catch (err) {
    console.error(err);
  }
}

function _omit(obj, omitKey) {
  const newObj = {};

  for (let key in obj) {
    if (key !== omitKey) newObj[key] = obj[key];
  }

  return newObj;
}

function getItemFromForm() {
  // subtract the submit button
  const fieldsNumber = itemForm.length - 1;
  const item = {};

  for (let i = 0; i < fieldsNumber; i++) {
    const field = itemForm[i];
    const key = field.id;
    const value = field.value;

    // don't want id with post request body
    if (key === "itemId" && update) item.id = value;
    else if (key !== "itemId") item[key] = value;
  }

  return item;
}

function setFormToUpdate() {
  update = true;

  itemIdInput.hidden = false;
  formTitle.textContent = "Edit";
}

function setFormToCreate() {
  update = false;

  itemForm.reset();
  itemIdInput.hidden = true;
  formTitle.textContent = "Add";
}

function getFormFields(fieldNames) {
  const formFields = [];

  fieldNames.forEach((fieldName) => {
    const field = document.getElementById(fieldName);
    formFields.push(field);
  });

  return formFields;
}

function setFormFieldsToItemValues(formFields, item) {
  formFields.forEach((formField) => {
    formField.value = item[formField.id];
  });
}

function fillItemWithFieldsValues(item, fields) {
  fields.forEach((field) => {
    const fieldName = field.dataset.fieldname;
    const value = field.textContent;

    item[fieldName] = value;
  });
}

function updateItemForm(itemId) {
  const fields = document.querySelectorAll(`.item-${itemId}`);
  const item = {
    itemId,
  };

  fillItemWithFieldsValues(item, fields);
  const formFields = getFormFields(Object.keys(item));

  setFormFieldsToItemValues(formFields, item);
  setFormToUpdate();
}

function deleteItem(itemId) {
  sendDeleteRequest(itemId);
}
