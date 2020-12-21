function search(allItemList, id) {
    $('#search').on('keyup', function () {
        var value = $(this).val();
        console.log('Value = ' + value);
        const data = filterFunction(value, allItemList);
        rebuildTable(data, id)
    });
}

function filterFunction(value, data) {
    const filteredData = [];
    for (let i = 0; i < data.length; i++) {
        value = value.toLowerCase();
        const type = data[i].bikeModel.type.toLowerCase();
        const bikeModel = data[i].bikeModel.name.toLowerCase();
        const bikeBrand = data[i].bikeModel.brand.toLowerCase();

        if (type.includes(value) || bikeModel.includes(value)  || bikeBrand.includes(value)) {
            filteredData.push(data[i]);
        }
    }
    return filteredData;
}

function rebuildTable(data, id) {
    const table = document.getElementById('bikeList');
    let rows = `<tr>
                <th>ID</th>
                <th>Cena</th>
                <th>Stan</th>
                <th>Model</th>
                <th>Marka</th>
                <th>Typ</th>
                <th>Wypożycz</th>
            </tr>`
    for (let i = 0; i < data.length; i++) {
        const row = `<tr>
                <td>${data[i].id}</td>
                <td>${data[i].pricePerHour}</td>
                <td>${data[i].bikeState}</td>
                <td>${data[i].bikeModel?.name}</td>
                <td>${data[i].bikeModel?.brand}</td>
                <td >${data[i].bikeModel?.type}</td>
                <td>
                    <a href="/rentalOffice/${id}/bike/${data[i].id}">Wypożycz</a>
                </td>
                </tr>`
        rows += row
    }
    table.innerHTML = rows;
}