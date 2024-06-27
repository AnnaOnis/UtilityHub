function updateServiceDetails(services) {

    let serviceId = document.getElementById('service').value;
    let selectedService = services.find(service => service.id == serviceId);

    if (selectedService) {
        document.getElementById('measureUnit').textContent = selectedService.measureUnit;
        document.getElementById('tariff').textContent = selectedService.tariff;
        updateAmount();
    }
}

function updateAmount() {
    let tariff = parseFloat(document.getElementById('tariff').textContent);
    let quantity = parseFloat(document.getElementById('quantity').value);
    let amount = tariff * quantity;
    document.getElementById('amount').value = isNaN(amount) ? '' : amount.toFixed(2);
}