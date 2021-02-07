import request from '@/utils/request'

                                                                    
// 分页查询一费制学员教练关联表列表
export function listPageOneFeeSystemCoachStudent(query) {
  return request({
    url: '/admin/oneFeeSystemCoachStudent/pageList',
    method: 'post',
    params: query
  })
}


// 分页查询一费制学员教练关联表列表
export function listOneFeeSystemCoachStudent(query) {
  return request({
    url: '/admin/oneFeeSystemCoachStudent/findList',
    method: 'post',
    params: query
  })
}

// 查询一费制学员教练关联表详细
export function getOneFeeSystemCoachStudent(id) {
  return request({
    url: '/admin/oneFeeSystemCoachStudent/' + id,
    method: 'get'
  })
}

// 新增一费制学员教练关联表
export function addOneFeeSystemCoachStudent(data) {
  return request({
    url: '/admin/oneFeeSystemCoachStudent',
    method: 'post',
    data: data
  })
}

// 修改一费制学员教练关联表
export function updateOneFeeSystemCoachStudent(data) {
  return request({
    url: '/admin/oneFeeSystemCoachStudent',
    method: 'put',
    data: data
  })
}

// 删除一费制学员教练关联表
export function delOneFeeSystemCoachStudent(id) {
  return request({
    url: '/admin/oneFeeSystemCoachStudent/' + id,
    method: 'delete'
  })
}


// 状态启用/停用一费制学员教练关联表
export function changeStatus(data) {
  return request({
    url: '/admin/oneFeeSystemCoachStudent/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出一费制学员教练关联表
export function exportOneFeeSystemCoachStudent(query) {
  return request({
    url: '/admin/oneFeeSystemCoachStudent/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}