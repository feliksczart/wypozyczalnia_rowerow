function search(allItemList) {
    $('#search').on('keyup', function () {
        var value = $(this).val();
        console.log('Value = ' + value);
        const data = filterFunction(value, allItemList);
        rebuildTable(data)
    });
}

function filterFunction(value, data) {
    const filteredData = [];
    for (let i = 0; i < data.length; i++) {
        value = value.toLowerCase();
        const start_date = data[i].startDate.toLowerCase();
        const model = data[i].bike.bikeModel.name.toLowerCase();
        const city = data[i].rentalOffice.city.toLowerCase();

        if (city.includes(value) || start_date.includes(value)  || model.includes(value)) {
            filteredData.push(data[i]);
        }
    }
    return filteredData;
}

function rebuildTable(data) {
    const table = document.getElementById('rentalsList'); //id tabeli
    let rows = `<tr class="table__head">
            <th>Data Rozpoczęcia</th>
            <th>Data Zakończenia</th>
            <th>Cena</th>
            <th>Nazwa Roweru</th>
            <th>Nazwisko Użytkownika</th>
            <th>Miasto</th>
            <th>Zakończ Wypożyczenie</th>
            <th>Reklamacja</th>
        </tr>`
    for (let i = 0; i < data.length; i++) {
        const row = `<tr>
            <td>${data[i].startDate}</td>
            <td>${data[i]?.endDate}</td>
            <td>${data[i]?.price}</td>
            <td>${data[i].bike.bikeModel.name}</td>
            <td>${data[i].user?.surname}</td>
            <td>${data[i].rentalOffice.city}</td>
            <td>
                <span if="${data[i]?.endDate != null}">Zakończono</span>
                <span if="${data[i]?.endDate == null}"><a href="@{/myRentals/${data[i].id}">Zakończ Wypożyczenie</a></span>
            </td>
            <td>
            <span if="${data[i]?.complaints == null}"><a href="@{/myRentals/${data[i].id}/complaint">złóż</a></span>
                <span if="${data[i]?.complaints != null}"><a href="@{/myRentals/${data[i].id}/show">zobacz</a></span>
            </td>
        </tr>`
        rows += row
    }
    table.innerHTML = rows;
}