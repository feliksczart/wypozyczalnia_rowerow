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
        const city = data[i].city.toLowerCase();
        const address = data[i].address.toLowerCase();
        const zip = data[i].zip.toLowerCase();
        const voivodeship = data[i].region.voivodeship.toLowerCase();
        const district = data[i].region.district.toLowerCase();

        if (city.includes(value) || address.includes(value)  || zip.includes(value) || voivodeship.includes(value) || district.includes(value)) {
            filteredData.push(data[i]);
        }
    }
    return filteredData;
}

function rebuildTable(data, id) {
    const table = document.getElementById('rentalOfficeList'); //id tabeli
    let rows = `<tr class="table__head">
        <th>Miasto</th>
        <th>Ulica</th>
        <th>Kod Pocztowy</th>
        <th>Województwo</th>
        <th>Powiat</th>
        <th>Rowery</th>
    </tr>`
    for (let i = 0; i < data.length; i++) {
        const row = `<tr>
        <td>${data[i].city}</td>
        <td>${data[i].address}</td>
        <td>${data[i].zip}</td>
        <td>${data[i].region?.voivodeship}</td>
        <td>${data[i].region?.district}</td>
        <td><a href="@{/rentalOffice/${id}">Zobacz Rowery</a></td>
    </tr>`
        rows += row

    }
    table.innerHTML = rows;
}