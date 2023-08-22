<template>
  <table>
    <thead>
      <tr>
        <th v-for="(header, index) in props.headerText" :key="index">
          {{ header }}
        </th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="item in props.data" :key="item.id" @click="onClickTr(item.id)">
        <td v-for="key in props.dataKey" :key="`${item.id}-${key}`">
          <span>{{ item[key] }} </span>
        </td>
      </tr>
    </tbody>
  </table>
</template>

<script setup lang="ts">
import type { dataType, headerType } from '@/types/table';
import type { PropType } from 'vue';

const props = defineProps({
  data: {
    type: Array<dataType>,
    required: true
  },
  headerText: {
    type: Object as PropType<headerType>,
    required: true
  },
  dataKey: {
    type: Array<string>,
    required: true
  }
});

const emits = defineEmits(['click']);

const onClickTr = (id: number) => {
  emits('click', id);
};
</script>

<style scoped>
body {
  padding: 1.5em;
  background: #f5f5f5;
}

table {
  border: 1px #a39485 solid;
  font-size: 0.9em;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.25);
  width: 100%;
  border-collapse: collapse;
  border-radius: 5px;
  overflow: hidden;
}

th {
  text-align: left;
}

thead {
  font-weight: bold;
  color: #fff;
  background: #73685d;
}

td,
th {
  padding: 1em 0.5em;
  vertical-align: middle;
}

td {
  border-bottom: 1px solid rgba(0, 0, 0, 0.1);
  background: #fff;
}

a {
  color: #73685d;
}

@media all and (max-width: 768px) {
  table,
  thead,
  tbody,
  th,
  td,
  tr {
    display: block;
  }

  th {
    text-align: right;
  }

  table {
    position: relative;
    padding-bottom: 0;
    border: none;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  }

  thead {
    float: left;
    white-space: nowrap;
  }

  tbody {
    overflow-x: auto;
    overflow-y: hidden;
    position: relative;
    white-space: nowrap;
  }

  tr {
    display: inline-block;
    vertical-align: top;
  }

  th {
    border-bottom: 1px solid #a39485;
  }

  td {
    border-bottom: 1px solid #e5e5e5;
  }
}
</style>
