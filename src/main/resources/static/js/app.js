const bookingForm = document.querySelector("#bookingForm");
const formStatus = document.querySelector("#formStatus");
const appointmentTimeInput = document.querySelector("#appointmentTime");

function toDatetimeLocalValue(date) {
    const offsetDate = new Date(date.getTime() - date.getTimezoneOffset() * 60000);
    return offsetDate.toISOString().slice(0, 16);
}

function setDefaultAppointmentTime() {
    const twoHoursLater = new Date(Date.now() + 2 * 60 * 60 * 1000);
    appointmentTimeInput.value = toDatetimeLocalValue(twoHoursLater);
    appointmentTimeInput.min = toDatetimeLocalValue(new Date());
}

setDefaultAppointmentTime();

function setStatus(message, type = "info") {
    formStatus.classList.remove("success", "error");
    if (type !== "info") {
        formStatus.classList.add(type);
    }
    formStatus.textContent = message;
}

function getErrorMessage(data) {
    if (!data || typeof data !== "object") {
        return "提交失败，请稍后再试。";
    }

    if (typeof data.message === "string" && data.message.trim()) {
        return data.message;
    }

    const fieldErrors = Object.entries(data)
        .filter(([key, value]) => key !== "timestamp" && key !== "status" && key !== "error" && key !== "path" && typeof value === "string")
        .map(([, value]) => value);

    return fieldErrors[0] || "提交失败，请检查表单信息。";
}

bookingForm.addEventListener("submit", async (event) => {
    event.preventDefault();
    setStatus("正在提交预约...");

    const payload = Object.fromEntries(new FormData(bookingForm).entries());

    try {
        const response = await fetch("/api/bookings", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(payload)
        });

        const data = await response.json().catch(() => ({}));
        if (!response.ok) {
            throw new Error(getErrorMessage(data));
        }

        setStatus(`提交成功：${data.message || "预约已提交。"} 预约编号：${data.bookingNo}`, "success");
        bookingForm.reset();
        setDefaultAppointmentTime();
    } catch (error) {
        const message = error.message || "网络异常，请稍后再试。";
        setStatus(message.startsWith("提交失败") ? message : `提交失败：${message}`, "error");
    }
});
