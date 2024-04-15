<template>
    <div class="center">
        <el-table :data="tableData" height="250" style="width: 100%">
            <el-table-column prop="sid" label="学号" width="250" />
            <el-table-column prop="name" label="姓名" width="250" />
            <el-table-column label="性别" width="250">
                <template #default="scope">
                    <span>{{ scope.row.gender === 'f' ? '女' : scope.row.gender === 'm' ? '男' : '' }}</span>
                </template>
            </el-table-column>
            <el-table-column prop="age" label="年龄" width="250" />
            <el-table-column prop="birthday" label="生日" width="250" />
            <el-table-column label="操作" width="250">
                <template #default="scope">
                    <el-button size="small" @click="handleEdit(scope.$index, scope.row)">修改</el-button>
                    <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>


        <el-dialog v-model="dialogTableVisible" title="学生信息" width="500">
            <el-form :model="form">
                <el-form-item label="学号" :label-width="formLabelWidth">
                    <el-input v-model="form.sid" autocomplete="off" readonly />
                </el-form-item>
                <el-form-item label="姓名" :label-width="formLabelWidth">
                    <el-input v-model="form.name" autocomplete="off" />
                </el-form-item>
                <el-form-item label="性别" :label-width="formLabelWidth">
                    <el-select v-model="form.gender" placeholder="请选择性别">
                        <el-option label="男" value="m" />
                        <el-option label="女" value="f" />
                    </el-select>
                </el-form-item>
                <el-form-item label="年龄" :label-width="formLabelWidth">
                    <el-input v-model="form.age" autocomplete="off" />
                </el-form-item>
                <el-form-item label="生日" :label-width="formLabelWidth">
                    <el-date-picker v-model="form.birthday" type="date" placeholder="选择生日" />
                </el-form-item>
            </el-form>
            <template #footer>
                <div class="dialog-footer">
                    <el-button @click="dialogTableVisible = false">取消</el-button>
                    <el-button type="primary" @click="handleSubmit()">提交</el-button>
                </div>
            </template>
        </el-dialog>




        <br><br>
        <div class="center-button">
            <el-button plain @click="handelAdd">添加学生</el-button>
        </div>

        <el-dialog v-model="dialogFormVisible" title="学生信息" width="500">
            <el-form :model="form">
                <el-form-item label="学号" :label-width="formLabelWidth">
                    <el-input v-model="form.sid" autocomplete="off" />
                </el-form-item>
                <el-form-item label="姓名" :label-width="formLabelWidth">
                    <el-input v-model="form.name" autocomplete="off" />
                </el-form-item>
                <el-form-item label="性别" :label-width="formLabelWidth">
                    <el-select v-model="form.gender" placeholder="请选择性别">
                        <el-option label="男" value="m" />
                        <el-option label="女" value="f" />
                    </el-select>
                </el-form-item>
                <el-form-item label="年龄" :label-width="formLabelWidth">
                    <el-input v-model="form.age" autocomplete="off" />
                </el-form-item>
                <el-form-item label="生日" :label-width="formLabelWidth">
                    <el-date-picker v-model="form.birthday" type="date" placeholder="选择生日" />
                </el-form-item>
            </el-form>
            <template #footer>
                <div class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取消</el-button>
                    <el-button type="primary" @click="addStudent">提交</el-button>
                </div>
            </template>
        </el-dialog>

    </div>

</template>


<script setup>
import { ref, reactive } from 'vue'
import { onMounted } from 'vue';
import axios from 'axios'
import { ElMessage, ElMessageBox } from 'element-plus';
import { useRouter } from 'vue-router';


const router = useRouter();

onMounted(() => {
    findAll();
    if (sessionStorage.getItem('login') != 'true') {
        ElMessage.error('未登录！请先登录');
        router.push('/');
    }
})






const dialogFormVisible = ref(false)
const dialogTableVisible = ref(false)
const formLabelWidth = '140px'

const form = ref({
    sid: '',
    name: '',
    gender: '',
    age: '',
    birthday: '',
})

function handleEdit(index, row) {

    const sid = row.sid;
    const student = tableData.value.find(student => student.sid === sid);
    form.value = { ...student };
    dialogTableVisible.value = true;


}


function handelAdd() {
    dialogFormVisible.value = true;
    form.value = {
        sid: '',
        name: '',
        gender: '',
        age: '',
        birthday: '',
    }
}


function addStudent() {

    dialogFormVisible.value = false;
    axios.post('http://localhost:8080/student/add', form.value)
        .then((res) => {
            if (res.data.code == 200) {
                ElMessage.success("学生信息添加成功！");
                form.value = {
                    sid: '',
                    name: '',
                    gender: '',
                    age: '',
                    birthday: '',
                }
                findAll();
            }
            else {
                ElMessage.error("添加失败！");
            }
        })
        .catch(() => {
            ElMessage.error("请求失败！");
        })
}









const tableData = ref([]);
function findAll() {
    axios.get('http://localhost:8080/student/list')
        .then((res) => {
            tableData.value = res.data;
            console.log(tableData.value);
        })
}


function handleDelete(index, row) {

    ElMessageBox.confirm(
        '确定要删除吗?',
        'Warning',
        {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(() => {
            let sid = row.sid
            axios.delete("http://localhost:8080/student/" + sid)
                .then((res) => {
                    ElMessage({
                        type: 'success',
                        message: '删除成功',
                    })
                    findAll();

                })

        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: '删除取消',
            })
        })


    console.log(index)
    console.log(row.sid)
}

function handleSubmit() {
    dialogTableVisible.value = false;
    axios.put('http://localhost:8080/student/modify', form.value)
        .then((res) => {
            if (res.data.code == 200) {
                ElMessage.success("学生信息修改成功！");
                form.value = {
                    sid: '',
                    name: '',
                    gender: '',
                    age: '',
                    birthday: '',
                }
                findAll();
            }
            else {
                ElMessage.error("修改失败！");
            }
        })
        .catch(() => {
            ElMessage.error("请求失败！");
        })

}





</script>




<style scoped>
.center {
    margin-top: 10%;
}

.center-button {
    display: flex;
    justify-content: center;
    width: 100%;
}
</style>