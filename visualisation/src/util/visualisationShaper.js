
const colors = [
    'red',
    'orange',
    'yellow',
    'green',
    'blue',
    'indigo',
    'violet',
    'brown',
];

export const visualisationShaper = (data) => {
    const genesData = [];
    const countData = [];
    const tableData = []

    for (let i = 0 ; i < data.length; i++){
        const current = data[i];
        genesData.push({
            values: current.genesFrequency.map((value, index) => ({
                id: index,
                label: `Gene ${index}`,
                value,
                color: colors[index],
            }))
        });
        countData.push({
            animal: current.animalCount,
            grass: current.grassCount,
        });

        tableData.push({
            energy: current.averageEnergy,
            children: current.averageChildrenNum,
            life: current.averageLifeLength,
        })
    }
    return { genesData, countData, tableData };
}