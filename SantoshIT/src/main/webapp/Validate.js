const form = document.getElementById('form');
const fname = document.getElementById('fName');
const lname = document.getElementById('lName');
const phoneNo = document.getElementById('phNo');
const password = document.getElementById('password');
const mail = document.getElementById('mail');

form.addEventListener('submit', e => {
    e.preventDefault();
    validateInputs();
});

const setError = (element, message) => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.error');
    errorDisplay.innerText = message;
    inputControl.classList.add('error');
    inputControl.classList.remove('success');
};

const setSuccess = (element) => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.error');
    errorDisplay.innerText = "";
    inputControl.classList.remove('error');
    inputControl.classList.add('success');
};

const setErrorMail = (message) => {
    const inputControl = document.querySelector('.input-control-mail');
    const errorDisplay = document.querySelector('.emailSet');
    errorDisplay.innerText = message;
    inputControl.classList.add('error');
    inputControl.classList.remove('success');
};

const setSuccessMail = () => {
    const inputControl = document.querySelector('.input-control-mail');
    const errorDisplay = document.querySelector('.emailSet');
    errorDisplay.innerText = "";
    inputControl.classList.remove('error');
    inputControl.classList.add('success');
};

// Async check for phone & email existence
const checkPhoneAndEmail = (phone, mail, callback) => {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "SignUpValid", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onload = function () {
        if (xhr.status === 200) {
            let response = JSON.parse(xhr.responseText.trim());
			
            if(response.phoneMessage && response.phoneMessage.trim() !== "") {
                setError(phoneNo, response.phoneMessage);
            } else {
                setSuccess(phoneNo);
            }

            if(response.mailMessage && response.mailMessage.trim() !== "") {
                setErrorMail(response.mailMessage);
            } else {
                setSuccessMail();
            }

            callback(!(response.phoneMessage.trim() || response.mailMessage.trim()));
        }
    };
    xhr.send(`phNo=${encodeURIComponent(phone)}&email=${encodeURIComponent(mail)}`);
};

const validateInputs = () => {
    let fn = false, ln = false, phno = false, pass = false, mailCheck = false;

    const fnameVal = fname.value.trim();
    const lnameVal = lname.value.trim();
    const phoneNoVal = phoneNo.value.trim();
    const passwordVal = password.value.trim();
    const emailVal = mail.value.trim();

    // First name
    if (fnameVal === '') {
        setError(fname, '* First name is required');
    } else {
        setSuccess(fname);
        fn = true;
    }

    // Last name
    if (lnameVal === '') {
        setError(lname, '* Last name is required');
    } else {
        setSuccess(lname);
        ln = true;
    }

    // Phone number
    if (phoneNoVal === '') {
        setError(phoneNo, '* Phone number is required');
    } else if (phoneNoVal.length !== 10) {
        setError(phoneNo, 'Please enter a valid phone number');
    } else {
        phno = true;
    }

    // Password
    if (passwordVal === '') {
        setError(password, '* Password is required');
    } else if (passwordVal.length < 8) {
        setError(password, 'Minimum 8 characters required');
    } else {
        setSuccess(password);
        pass = true;
    }

    // Email
    if (emailVal === '') {
        setErrorMail('* Email is required');
    } else {
		setSuccessMail();
        mailCheck = true;
    }

    // Ajax server-side validation only if all fields filled
    if (fn && ln && phno && pass && mailCheck) {
        checkPhoneAndEmail(phoneNoVal, emailVal, function (isOk) {
            if (isOk) {
                form.action = "./joinStu";
                form.submit();
            }
        });
    }
};
